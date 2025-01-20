package br.mil.mar.saudenaval.sharing.services.A4;

import br.mil.mar.saudenaval.sharing.config.CreateTime;
import br.mil.mar.saudenaval.sharing.config.FileUpload;
import br.mil.mar.saudenaval.sharing.domain.A4.PapelOficioData;
import br.mil.mar.saudenaval.sharing.entities.A4.PapelOficio;
import br.mil.mar.saudenaval.sharing.config.Search;
import br.mil.mar.saudenaval.sharing.repositories.PapelOficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PapelOficioServices {

    @Autowired
    private PapelOficioRepository repository;


    public URI buildImageURL(PapelOficio cartaz){
        String path = "/poster/" + cartaz.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(path)
                .build()
                .toUri();
    }

    public URI buildPdfURL(PapelOficio doc){
        String path = "/doc/" + doc.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(path)
                .build()
                .toUri();
    }

    public void create(MultipartFile poster, MultipartFile file, PapelOficioData data) throws IOException {

        PapelOficio cartaz = new PapelOficio();
        cartaz.setTitle(data.getTitle());
        cartaz.setImage(FileUpload.save("/A4",poster));
        cartaz.setImageFilename(poster.getOriginalFilename());
        cartaz.setAno(data.getAno());
        cartaz.setFile(FileUpload.save("/A4",file));
        cartaz.setFilename(file.getOriginalFilename());
        cartaz.setCreatedAt(CreateTime.now());

        repository.save(cartaz);
    }

    public List<PapelOficio> getAll(){

        List<PapelOficio> cartazes = repository.findAll();

        return cartazes.stream()
                .sorted(Comparator.comparing(PapelOficio::getCreatedAt))
                .collect(Collectors.toList());
    }


    public ResponseEntity<Resource> createImage(String id){
        var possibleImage = repository.findById(id);
        if(possibleImage .isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        PapelOficio image = possibleImage.orElse(null);
        String filename = image.getImageFilename();

        return FileUpload.download("/A4",filename);
    }

    @Transactional
    public ResponseEntity<Resource> createDocument(String id){
        var possibleDoc = repository.findById(id);
        if(possibleDoc.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        PapelOficio document = possibleDoc.orElse(null);

        repository.setDownloded(id);

        String filename = document.getFilename();
        return FileUpload.download("/A4",filename);

    }

    public List<PapelOficio> findByYearOrTitle(Search search){

        List<PapelOficio> cartazes = repository.findByYearOrTitle(search.getAno(), search.getTitle());

        return cartazes.stream()
                .sorted(Comparator.comparing(PapelOficio::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

        public void updateCartaz(PapelOficioData data,MultipartFile file, MultipartFile image) throws IOException {
            PapelOficio cartaz = repository.findById(data.getId()).orElse(null);

            cartaz.setTitle(data.getTitle());
            cartaz.setAno(data.getAno());
            if(!file.isEmpty()){
                cartaz.setFile(FileUpload.save("/A4",file));
                cartaz.setFilename(file.getOriginalFilename());
            }

            if(!image.isEmpty()){
                cartaz.setImage(FileUpload.save("/A4",image));
                cartaz.setImageFilename(image.getOriginalFilename());
            }
            repository.save(cartaz);

        }

    public void removeCartaz(String id){
        repository.deleteById(id);
    }


}
