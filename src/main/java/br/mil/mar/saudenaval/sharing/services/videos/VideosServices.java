package br.mil.mar.saudenaval.sharing.services.videos;

import br.mil.mar.saudenaval.sharing.config.CreateTime;
import br.mil.mar.saudenaval.sharing.config.FileUpload;
import br.mil.mar.saudenaval.sharing.domain.videos.VideosData;
import br.mil.mar.saudenaval.sharing.entities.videos.Videos;
import br.mil.mar.saudenaval.sharing.repositories.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VideosServices {

    @Autowired
    private VideosRepository repository;

    private final String folder = "/videos";

    public void create(VideosData videos, MultipartFile file) throws IOException {

        String filepath = FileUpload.save(folder,file);
        Videos video = new Videos();
        video.setTitle(videos.getTitle());
        video.setAno(videos.getAno());
        video.setFile(filepath);
        video.setFilename(file.getOriginalFilename());
        video.setContentType(file.getContentType());
        video.setCreatedAt(CreateTime.now());
        repository.save(video);

    }

    public List<Videos> findAllByYear(String ano){
        List<Videos> list = repository.findAllByYear(ano);
        list.stream().sorted(Comparator.comparing(Videos::getCreatedAt).reversed()).toList();
        return list;
    }

    public List<Videos> findByTitle(String title){
        List<Videos> list = repository.findByTitle(title);
        list.stream().sorted(Comparator.comparing(Videos::getCreatedAt).reversed()).toList();
        return list;
    }

    @Transactional
    public ResponseEntity<Resource> download(String id){
        var possibleVideo = repository.findById(id);
        Videos video = possibleVideo.orElse(null);

        if (video != null){
            repository.setDownloaded(id);
           return FileUpload.download(folder,video.getFilename());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public void update(VideosData data, MultipartFile file) throws IOException{
        var possibleVideo = repository.findById(data.getId());

        Videos video = possibleVideo.orElse(null);
        if (video != null){
            video.setTitle(data.getTitle());
            video.setAno(data.getAno());
            if (!file.isEmpty()){
                String url = FileUpload.save(folder,file);
                video.setFile(url);
                video.setFilename(file.getOriginalFilename());
                video.setContentType(file.getContentType());
            }
            repository.save(video);
        }
    }

    public void remove(String id) throws IOException {
        var possibleVideo = repository.findById(id);

        Videos video = possibleVideo.orElse(null);
        if (video != null){
            FileUpload.removeFile(folder,video.getFilename());
            repository.deleteById(id);
        }
    }

    public List<Videos> findAll(){
        List<Videos> list = repository.findAll();
        list.stream().sorted(Comparator.comparing(Videos::getCreatedAt).reversed()).collect(Collectors.toList());
        return list;
    }
}
