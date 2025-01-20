package br.mil.mar.saudenaval.sharing.controller.sinalizacao;

import br.mil.mar.saudenaval.sharing.domain.sinalizacao.SinalData;
import br.mil.mar.saudenaval.sharing.entities.sinalizacao.Sinalizacao;
import br.mil.mar.saudenaval.sharing.services.sinalizacao.SinalizacaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sinalizacao")
public class SinalizacaoController {

    @Autowired
    private SinalizacaoServices services;

    @PostMapping("/create")
    public ResponseEntity create(@RequestPart("data") SinalData data, @RequestPart("file") MultipartFile file){
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
    public ResponseEntity<List<Sinalizacao>> findAll(){
        List<Sinalizacao> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id){
        return services.download(id);
    }

    @PutMapping("/edit")
    public ResponseEntity update(@RequestPart("data") SinalData data, @RequestPart("file") MultipartFile file){
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
    public ResponseEntity<List<Sinalizacao>> search(@RequestBody SinalData data){
        List<Sinalizacao> list = services.search(data);
        return ResponseEntity.ok().body(list);
    }

}
