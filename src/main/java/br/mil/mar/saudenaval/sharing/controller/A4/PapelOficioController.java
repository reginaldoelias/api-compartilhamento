package br.mil.mar.saudenaval.sharing.controller.A4;

import br.mil.mar.saudenaval.sharing.domain.A4.PapelOficioData;
import br.mil.mar.saudenaval.sharing.entities.A4.PapelOficio;
import br.mil.mar.saudenaval.sharing.config.Search;
import br.mil.mar.saudenaval.sharing.services.A4.PapelOficioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/a4")
public class PapelOficioController {

    @Autowired
    private PapelOficioServices services;


    @PostMapping("/create")
    public ResponseEntity createA4(@RequestPart("file") MultipartFile file, @RequestPart("image") MultipartFile image, @RequestPart("data") PapelOficioData papelOficioData) {

        ResponseEntity response;
        try {
            services.create(image, file, papelOficioData);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
            response = ResponseEntity.badRequest().build();
            //  throw new RuntimeException(e);

        }
        return response;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<PapelOficio> list = services.getAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/poster/{id}")
    public ResponseEntity<Resource> getPoster(@PathVariable String id) {
        // System.out.println(id);
        return services.createImage(id);
    }

    @GetMapping("/doc/{id}")
    public ResponseEntity<Resource> getDocument(@PathVariable String id) {
        return services.createDocument(id);
    }

    @PostMapping
    public ResponseEntity<List<PapelOficio>> findByYearOrTitle(@RequestBody Search search) {
        List<PapelOficio> list = services.findByYearOrTitle(search);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/edit")
    public ResponseEntity updateCartaz(@RequestPart("file") MultipartFile file, @RequestPart("image") MultipartFile image, @RequestPart("data") PapelOficioData papelOficioData) {

      ResponseEntity response;

       try {
          services.updateCartaz(papelOficioData,file,image);
          response = ResponseEntity.ok().build();
       }catch (IOException exception){
           response = ResponseEntity.badRequest().build();
       }

        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteCartaz(@PathVariable String id) {
        services.removeCartaz(id);
        return ResponseEntity.ok().build();
    }
}
