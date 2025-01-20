package br.mil.mar.saudenaval.sharing.controller.PD;

import br.mil.mar.saudenaval.sharing.domain.PD.PlanoDoDiaData;
import br.mil.mar.saudenaval.sharing.entities.PD.PlanoDoDia;
import br.mil.mar.saudenaval.sharing.services.PD.PlanoDoDiaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pd")
public class PlanoDoDiaController {

    @Autowired
    private PlanoDoDiaServices services;

    @PostMapping("/create")
    public ResponseEntity create(@RequestPart("data")PlanoDoDiaData data, @RequestPart("file")MultipartFile file){

        ResponseEntity response;
        try{
            services.create(data,file);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
           // throw new RuntimeException(e);
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable String id) {
      //  System.out.println(id);
        return services.createImage(id);
    }

    @GetMapping("/{ano}")
    public ResponseEntity<List<PlanoDoDia>> findAll(@PathVariable String ano){
        return services.findAllByYear(ano);
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<List<PlanoDoDia>> enviarEmail(@PathVariable String id){
        return services.enviarEmail(id);
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        return services.download(filename);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PlanoDoDia>> search(@RequestBody PlanoDoDia data){
        List<PlanoDoDia> list = services.findByTitle(data.getTitle());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<PlanoDoDia>> findAll(){
        List<PlanoDoDia> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

}
