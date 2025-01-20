package br.mil.mar.saudenaval.sharing.controller.institucional;

import br.mil.mar.saudenaval.sharing.config.Search;
import br.mil.mar.saudenaval.sharing.domain.institucional.InstitucionalData;
import br.mil.mar.saudenaval.sharing.entities.institucional.Institucional;
import br.mil.mar.saudenaval.sharing.services.institucional.InstitucionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/institucional")
public class InstitucionalController {

    @Autowired
    private InstitucionalService service;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity createInstitucional(@RequestPart("file") MultipartFile file, @RequestPart("institucionalData") InstitucionalData institucionalData){

        ResponseEntity response;
        try{
             service.create(institucionalData,file);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
           // throw new RuntimeException(e);
            response = ResponseEntity.badRequest().build();
        }

        return  response;
    }

    @GetMapping("/relatorios")
    public ResponseEntity<List<Institucional>> getAll(){
        List<Institucional> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/relatorios/{id}")
    public ResponseEntity<Resource> getDocument(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/relatorios/filter")
    public ResponseEntity<List<Institucional>> filterDocuments(@RequestBody Search search){
        List<Institucional> list = service.filterList(search);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/relatorios/edit")
    public ResponseEntity updateRelatorios(@RequestPart("file") MultipartFile file, @RequestPart("institucionalData") InstitucionalData institucionalData){

        ResponseEntity response;

        try{
            service.updateRelatorios(file,institucionalData);
            response = ResponseEntity.ok().build();
        }  catch (IOException e) {
            response = ResponseEntity.badRequest().build();
          //  throw new RuntimeException(e);
        }

        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity remove(@PathVariable String id){

        if(id == null || id.isBlank()){
            return ResponseEntity.badRequest().build();
        }
        service.remove(id);
        return ResponseEntity.ok().build();
    }

}
