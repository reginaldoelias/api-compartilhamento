package br.mil.mar.saudenaval.sharing.controller.Covid;

import br.mil.mar.saudenaval.sharing.config.Search;
import br.mil.mar.saudenaval.sharing.entities.grafica.Grafica;
import br.mil.mar.saudenaval.sharing.services.Covid.CovidServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/covid")
public class CovidController {

    @Autowired
    private CovidServices services;

    @GetMapping
    public ResponseEntity<List<Grafica>> findAll(){
        List<Grafica> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Grafica>> search(@RequestBody Search search){
       List<Grafica> list = services.findByCategoryOrTitle(search.getTitle(), search.getTipo());
        return ResponseEntity.ok().body(list);
    }

}
