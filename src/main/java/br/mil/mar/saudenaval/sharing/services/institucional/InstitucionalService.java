package br.mil.mar.saudenaval.sharing.services.institucional;

import br.mil.mar.saudenaval.sharing.config.CreateTime;
import br.mil.mar.saudenaval.sharing.config.FileUpload;
import br.mil.mar.saudenaval.sharing.config.Search;
import br.mil.mar.saudenaval.sharing.domain.institucional.InstitucionalData;
import br.mil.mar.saudenaval.sharing.entities.institucional.Institucional;
import br.mil.mar.saudenaval.sharing.repositories.InstitucionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitucionalService {

    @Autowired
    private InstitucionalRepository repository;

    @Value("${file.upload-dir}")
    private String defaultPath;

     private final String folder = "/institucional";

    public void create(InstitucionalData institucionalData, MultipartFile file) throws IOException {

        LocalDate dataInicial = LocalDate.parse(institucionalData.getInicial());

        LocalDate dataFinal = null;
        if (!institucionalData.getEnd().isBlank()){
             dataFinal = LocalDate.parse(institucionalData.getEnd());
        }

        String ano = String.valueOf(dataInicial.getYear());

        Institucional institucional = new Institucional();
        institucional.setTitle(institucionalData.getTitle());
        institucional.setCategoria(institucionalData.getCategoria());
        institucional.setTipo(institucional.getTipo());
        institucional.setDataInicial(dataInicial);
        institucional.setDataFinal(dataFinal);
        institucional.setAno(ano);
        institucional.setFilename(file.getOriginalFilename());
        institucional.setExtension(file.getContentType());
        institucional.setFile(FileUpload.save(folder,file));
        institucional.setCreatedAt(CreateTime.now());
       repository.save(institucional);
    }




    public List<Institucional> findAll(){
        List<Institucional> lista = repository.findAll();
        lista.stream()
                .sorted(Comparator.comparing(Institucional::getCreatedAt)
                .reversed()).collect(Collectors.toList());
        return lista;
    }

    @Transactional
    public ResponseEntity<Resource> getById(String id){
        var possibleDoc = repository.findById(id);
        if(possibleDoc.isEmpty()){
           return ResponseEntity.notFound().build();
        }
        Institucional document = possibleDoc.orElse(null);
        String filename = document.getFilename();
        repository.setDownloded(id);
        return FileUpload.download(folder,filename);

    }




    public List<Institucional> filterList(Search search){
        return repository.filterDocs(search);
    }

    public void updateRelatorios(MultipartFile file, InstitucionalData data) throws IOException {

        Institucional institucional = repository.findById(data.getId()).orElse(null);
        if(institucional != null){
            institucional.setTitle(data.getTitle());
            institucional.setCategoria(data.getCategoria());
            institucional.setTipo(data.getTipo());
            institucional.setDataInicial(LocalDate.parse(data.getInicial()));
            institucional.setDataFinal(LocalDate.parse(data.getEnd()));
            LocalDate dataInicial = LocalDate.parse(data.getInicial());
            String ano = String.valueOf(dataInicial.getYear());
            institucional.setAno(ano);
            if(!file.isEmpty()){
                FileUpload.removeFile(folder,institucional.getFilename());
                FileUpload.save(folder,file);
                institucional.setFilename(file.getOriginalFilename());
            }
            repository.save(institucional);
        }

    }

    public void remove(String id){
        repository.deleteById(id);
    }
}
