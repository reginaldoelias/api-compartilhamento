package br.mil.mar.saudenaval.sharing.services.sinalizacao;

import br.mil.mar.saudenaval.sharing.config.CreateTime;
import br.mil.mar.saudenaval.sharing.config.FileUpload;
import br.mil.mar.saudenaval.sharing.domain.sinalizacao.SinalData;
import br.mil.mar.saudenaval.sharing.entities.sinalizacao.Sinalizacao;
import br.mil.mar.saudenaval.sharing.repositories.SinalizacaoRepository;
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
public class SinalizacaoServices {

    @Autowired
    private SinalizacaoRepository repository;

    private final String folder = "/sinalizacao";

    public void create(SinalData data, MultipartFile file) throws IOException {

        String arquivo =  FileUpload.save(folder,file);
        Sinalizacao sinal = new Sinalizacao();
        sinal.setTitle(data.getTitle());
        sinal.setTipo(data.getTipo());
        sinal.setCodigo(data.getCodigo());
        sinal.setAltura(data.getAltura());
        sinal.setMedida(data.getMedida());
        sinal.setFile(arquivo);
        sinal.setFilename(file.getOriginalFilename());
        sinal.setCreatedAt(CreateTime.now());
        sinal.setUpdatedAt(CreateTime.now());
        sinal.setDownload(0);
        repository.save(sinal);

    }

    public List<Sinalizacao> findAll(){

        List<Sinalizacao> list = repository.findAll();
        list.stream().sorted(Comparator.comparing(Sinalizacao::getCreatedAt)).collect(Collectors.toList());

        return list;
    }


    @Transactional
    public ResponseEntity<Resource> download(String id){
        var possibleDoc = repository.findById(id);
        Sinalizacao sinalizacao = possibleDoc.orElse(null);
        if (sinalizacao != null){
            repository.setDownloaded(id);
            return FileUpload.download(folder,sinalizacao.getFilename());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public void update(SinalData data, MultipartFile file) throws IOException{
        var possibleDoc = repository.findById(data.getId());
        Sinalizacao sinalizacao = possibleDoc.orElse(null);
        if (sinalizacao != null){
            sinalizacao.setTitle(data.getTitle());
            sinalizacao.setCodigo(data.getCodigo());
           sinalizacao.setTipo(data.getTipo());
            sinalizacao.setUpdatedAt(CreateTime.now());
            if(!file.isEmpty()){
                sinalizacao.setFile(FileUpload.save(folder,file));
                sinalizacao.setFilename(file.getOriginalFilename());
            }
            repository.save(sinalizacao);
        }
    }


    public ResponseEntity remove(String id) throws IOException{
        var possibleDoc = repository.findById(id);
        Sinalizacao sinalizacao = possibleDoc.orElse(null);

        if (sinalizacao != null){
            String filename = sinalizacao.getFilename();
            FileUpload.removeFile(folder,filename);
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public List<Sinalizacao> search(SinalData data){
        List<Sinalizacao> list = repository.search(data);
        list.stream().sorted(Comparator.comparing(Sinalizacao::getCreatedAt).reversed()).collect(Collectors.toList());
        return list;
    }


}
