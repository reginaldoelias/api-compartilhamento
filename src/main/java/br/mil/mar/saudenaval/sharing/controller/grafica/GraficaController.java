package br.mil.mar.saudenaval.sharing.controller.grafica;

import br.mil.mar.saudenaval.sharing.config.Search;
import br.mil.mar.saudenaval.sharing.domain.grafica.GraficaData;
import br.mil.mar.saudenaval.sharing.entities.grafica.Grafica;
import br.mil.mar.saudenaval.sharing.services.grafica.GraficaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/grafica")
public class GraficaController {

    @Autowired
    private GraficaServices services;


    @PostMapping("/create")
    public ResponseEntity create(@RequestPart("data") GraficaData grafica, @RequestPart("file") MultipartFile file, @RequestPart("image") MultipartFile image){

        ResponseEntity response;
        try{
            services.create(grafica,file,image);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
           // throw new RuntimeException(e);
           response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        return services.download(filename);
    }

    @GetMapping("/{tipo}")
    public ResponseEntity<List<Grafica>> findAll(@PathVariable String tipo){
        List<Grafica> list = services.findAllByType(tipo);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity setDownloadCount(@PathVariable String id){
        services.downloadCount(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<Grafica>> search(@RequestBody Search search){
        List<Grafica> list = services.search(search.getTitle());
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/edit")
    public ResponseEntity update(@RequestPart("data") GraficaData grafica, @RequestPart("file") MultipartFile file, @RequestPart("image") MultipartFile image){
        ResponseEntity response;
        try{
               services.update(grafica,file,image);
               response = ResponseEntity.ok().build();
           } catch (IOException e) {
                response = ResponseEntity.badRequest().build();
               //throw new RuntimeException(e);
           }
        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity remove(@PathVariable String id){
        ResponseEntity response;
        try{
            services.remove(id);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
           // throw new RuntimeException(e);
            response = ResponseEntity.badRequest().build();
        }
       return response;
    }

    @GetMapping
    public ResponseEntity<List<Grafica>> findAll(){
        List<Grafica> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }
}
