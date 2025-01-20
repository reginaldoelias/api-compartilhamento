package br.mil.mar.saudenaval.sharing.services.forms;

import br.mil.mar.saudenaval.sharing.config.CreateTime;
import br.mil.mar.saudenaval.sharing.config.FileUpload;
import br.mil.mar.saudenaval.sharing.domain.forms.FormData;
import br.mil.mar.saudenaval.sharing.entities.forms.Formularios;
import br.mil.mar.saudenaval.sharing.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormServices {

    @Autowired
    private FormRepository repository;

    private final String folder = "/formularios";

    public void create(FormData data, MultipartFile file) throws IOException {

        String arquivo =  FileUpload.save(folder,file);
        Formularios form = new Formularios();
        form.setTitle(data.getTitle());
        form.setCodigo(data.getCodigo());
        form.setEspecialidade(data.getEspecialidade());
        form.setFile(arquivo);
        form.setFilename(file.getOriginalFilename());
        form.setDownload(0);
        form.setCreatedAt(CreateTime.now());
        form.setUpdatedAt(CreateTime.now());
        repository.save(form);

    }


    public List<Formularios> findAll(){

        List<Formularios> list = repository.findAll();
        list.stream().sorted(Comparator.comparing(Formularios::getEspecialidade)).collect(Collectors.toList());

        return list;
    }

    @Transactional
    public ResponseEntity<Resource> download(String id){
        var possibleDoc = repository.findById(id);
        Formularios form = possibleDoc.orElse(null);
        if (form != null){
            repository.setDownloaded(id);
          return FileUpload.download(folder,form.getFilename());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public void update(FormData data, MultipartFile file) throws IOException{
        var possibleDoc = repository.findById(data.getId());
        Formularios form = possibleDoc.orElse(null);
        if (form != null){
            form.setTitle(data.getTitle());
            form.setCodigo(data.getCodigo());
            form.setEspecialidade(data.getEspecialidade());
            form.setUpdatedAt(CreateTime.now());
            if(!file.isEmpty()){
                form.setFile(FileUpload.save(folder,file));
                form.setFilename(file.getOriginalFilename());
            }
            repository.save(form);
        }
    }

    public ResponseEntity remove(String id) throws IOException{
        var possibleDoc = repository.findById(id);
        Formularios form = possibleDoc.orElse(null);

        if (form != null){
            String filename = form.getFilename();
            FileUpload.removeFile(folder,filename);
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public List<Formularios> search(FormData data){
        List<Formularios> list = repository.search(data);
        list.stream().sorted(Comparator.comparing(Formularios::getCreatedAt).reversed()).collect(Collectors.toList());
        return list;
    }

}
