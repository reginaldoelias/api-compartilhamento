package br.mil.mar.saudenaval.sharing.services.PD;

import br.mil.mar.saudenaval.sharing.config.CreateTime;
import br.mil.mar.saudenaval.sharing.config.FileUpload;
import br.mil.mar.saudenaval.sharing.domain.PD.PlanoDoDiaData;
import br.mil.mar.saudenaval.sharing.entities.PD.PlanoDoDia;
import br.mil.mar.saudenaval.sharing.entities.User;
import br.mil.mar.saudenaval.sharing.repositories.PlanoDoDiaRepository;
import br.mil.mar.saudenaval.sharing.repositories.UserRepository;
import br.mil.mar.saudenaval.sharing.services.email.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PlanoDoDiaServices {

    @Autowired
    private PlanoDoDiaRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    private final String folder = "/pd";

    public void create(PlanoDoDiaData data, MultipartFile file) throws IOException {
        PlanoDoDia pd = new PlanoDoDia();
        pd.setTitle(data.getTitle());
        pd.setAno(data.getAno());
        pd.setTexto(data.getTexto());
        pd.setStatus("Aguardando");
        String filePath = FileUpload.save(folder,file);
        pd.setFile(filePath);
        pd.setFilename(file.getOriginalFilename());
        pd.setCreatedAt(CreateTime.now());
        pd.setDownload(0);
        repository.save(pd);
    }

    public ResponseEntity<List<PlanoDoDia>> findAllByYear(String ano){
        List<PlanoDoDia> list = repository.findAllByYear(ano);
        list.stream().sorted(Comparator.comparing(PlanoDoDia::getCreatedAt).reversed()).toList();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<Resource> download(String filename){

        return FileUpload.download(folder,filename);
    }

    @Transactional
    public ResponseEntity<Resource> createImage(String id){
        var possibleImage = repository.findById(id);
        if(possibleImage .isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        PlanoDoDia image = possibleImage.orElse(null);
        String filename = image.getFilename();
        repository.setDownloded(id);
        return FileUpload.download(folder,filename);
    }

    public List<PlanoDoDia> findByTitle(String title){

        return repository.findByTitle(title);
    }

    public ResponseEntity<List<PlanoDoDia>> enviarEmail(String id){
        var possiblePd = repository.findById(id);

        if(possiblePd .isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        PlanoDoDia planoDoDia = possiblePd.orElse(null);
        String file = folder.concat("/").concat(planoDoDia.getFilename());

        List<User> users = userRepository.findAll();
        List<User> usuarios = users.stream().filter(user -> user.getPd().equals(true)).toList();


        ResponseEntity<List<PlanoDoDia>> response;

       try {
           emailService.sendMail(usuarios, planoDoDia.getTitle(), planoDoDia.getTexto(),file);
            possiblePd.ifPresent(pd->{
                pd.setStatus("Enviado");
                pd.setEnviados(usuarios.size());
                repository.save(pd);
            });

           List<PlanoDoDia> listPd = repository.findAll();
           listPd.stream().sorted(Comparator.comparing(PlanoDoDia::getCreatedAt).reversed()).toList();
           response = ResponseEntity.ok().body(listPd);
       } catch (MessagingException | IOException e) {
           //throw new RuntimeException(e);
           possiblePd.ifPresent(pd->{
               pd.setStatus("Falhou");
               repository.save(pd);
           });
         //  System.out.println(e.getLocalizedMessage());
           response = ResponseEntity.badRequest().body(new ArrayList<PlanoDoDia>());
       }


        return response;
    }

    public List<PlanoDoDia> findAll(){
        List<PlanoDoDia> list = repository.findAll();
        list.stream().sorted(Comparator.comparing(PlanoDoDia::getCreatedAt).reversed()).toList();
        return list;
    }
}
