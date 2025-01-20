package br.mil.mar.saudenaval.sharing.services.grafica;

import br.mil.mar.saudenaval.sharing.config.CreateTime;
import br.mil.mar.saudenaval.sharing.config.FileUpload;
import br.mil.mar.saudenaval.sharing.domain.grafica.GraficaData;
import br.mil.mar.saudenaval.sharing.entities.grafica.Grafica;
import br.mil.mar.saudenaval.sharing.repositories.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class GraficaServices {

    @Autowired
    private GraficaRepository repository;

    @Value("${file.upload-dir}")
    private String defaultPath;

    @Value("${server.url}")
    private String serverUrl;


    private final String folder = "/grafica";

    public void create(GraficaData data, MultipartFile file, MultipartFile image) throws IOException {

        String fileUrl = FileUpload.save(folder,file);
        String posterUrl = FileUpload.save(folder,image);


        Grafica grafica = new Grafica();
        grafica.setTitle(data.getTitle());
        grafica.setTipo(data.getTipo());
        grafica.setCategoria(data.getCategoria());
        grafica.setFileUrl(fileUrl);
        grafica.setImage(image.getOriginalFilename());
        grafica.setImageUrl(posterUrl);
        grafica.setFilename(file.getOriginalFilename());
        grafica.setCreatedAt(CreateTime.now());
        grafica.setDownload(0);
        repository.save(grafica);
    }


    public List<Grafica> findAllByType(String tipo){

        List<Grafica> list = repository.findAllByType(tipo);

       return list.stream().sorted(Comparator.comparing(Grafica::getCreatedAt).reversed())
               .collect(Collectors.toList());

    }

    public ResponseEntity<Resource> download(String filename){

        return FileUpload.download(folder,filename);

    }

    public List<Grafica> search(String title){
        List<Grafica> lista = repository.findByTitle(title);
        lista.stream().sorted(Comparator.comparing(Grafica::getCreatedAt).reversed()).toList();

        return lista;
    }

    @Transactional
    public void downloadCount(String id){
        repository.setDownloaded(id);
    }

    public void update(GraficaData grafica, MultipartFile file, MultipartFile image) throws IOException {
        var possibleDoc = repository.findById(grafica.getId());
        Grafica material = possibleDoc.orElse(null);
        if (material != null){
            material.setTitle(grafica.getTitle());
            material.setTipo(grafica.getTipo());
            material.setCategoria(grafica.getCategoria());
            if (!file.isEmpty()){
                material.setFilename(file.getOriginalFilename());
                material.setFileUrl(FileUpload.save(folder,file));
            }
            if (!image.isEmpty()){
                material.setImageUrl(FileUpload.save(folder,image));
                material.setImage(image.getOriginalFilename());
            }
            repository.save(material);
        }

    }

    public void remove(String id) throws IOException {
        var possibleDoc = repository.findById(id);
        Grafica material = possibleDoc.orElse(null);
        if(material != null){
            String image = material.getImage();
            String file = material.getFilename();
            FileUpload.removeFile(folder,image);
            FileUpload.removeFile(folder,file);
            repository.deleteById(id);
        }
    }

    public List<Grafica> findAll(){
        List<Grafica> list = repository.findAll();
        list.stream().sorted(Comparator.comparing(Grafica::getCreatedAt).reversed()).collect(Collectors.toList());
        return list;
    }
}
