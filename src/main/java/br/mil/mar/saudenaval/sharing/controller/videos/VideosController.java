package br.mil.mar.saudenaval.sharing.controller.videos;


import br.mil.mar.saudenaval.sharing.domain.videos.VideosData;
import br.mil.mar.saudenaval.sharing.entities.videos.Videos;
import br.mil.mar.saudenaval.sharing.services.videos.VideosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosServices services;

    @PostMapping("/create")
    public ResponseEntity create(@RequestPart("video") VideosData video, @RequestPart("file")MultipartFile file){
       ResponseEntity response;
        try{
            services.create(video, file);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
            response = ResponseEntity.badRequest().build();
            //throw new RuntimeException(e);
        }

        return response;
    }

    @GetMapping("/{ano}")
    public ResponseEntity<List<Videos>> findAll(@PathVariable String ano){
        List<Videos> list = services.findAllByYear(ano);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Videos>> search(@RequestBody VideosData data){
        List<Videos> list = services.findByTitle(data.getTitle());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id){
        return services.download(id);
    }

    @PutMapping("/edit")
    public ResponseEntity update(@RequestPart("data") VideosData data, @RequestPart MultipartFile file){
        ResponseEntity response;
        try{
            services.update(data,file);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
           // throw new RuntimeException(e);
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity remove(@PathVariable String id){
        ResponseEntity response;
        try {
            services.remove(id);
            response = ResponseEntity.ok().build();
        } catch (IOException e) {
          //  throw new RuntimeException(e);
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Videos>> findAll(){
        List<Videos> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

}
