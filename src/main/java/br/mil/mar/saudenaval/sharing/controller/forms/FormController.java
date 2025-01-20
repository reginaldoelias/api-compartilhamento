package br.mil.mar.saudenaval.sharing.controller.forms;


import br.mil.mar.saudenaval.sharing.domain.forms.FormData;
import br.mil.mar.saudenaval.sharing.entities.forms.Formularios;
import br.mil.mar.saudenaval.sharing.services.forms.FormServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/formularios")
public class FormController {

    @Autowired
    FormServices services;

    @PostMapping("/create")
    public ResponseEntity create(@RequestPart("data") FormData data, @RequestPart("file") MultipartFile file){
        ResponseEntity response;
        try{
            response = ResponseEntity.ok().build();
            services.create(data,file);
        } catch (IOException e) {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Formularios>> findAll(){
        List<Formularios> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id){
        return services.download(id);
    }

    @PutMapping("/edit")
    public ResponseEntity update(@RequestPart("data") FormData data, @RequestPart("file") MultipartFile file){
        ResponseEntity response;
        try{
            services.update(data,file);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity remove(@PathVariable String id){

        try {
            return services.remove(id);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Formularios>> search(@RequestBody FormData data){
        List<Formularios> list = services.search(data);
        return ResponseEntity.ok().body(list);
    }
}
