package br.mil.mar.saudenaval.sharing.services.Covid;

import br.mil.mar.saudenaval.sharing.entities.grafica.Grafica;
import br.mil.mar.saudenaval.sharing.repositories.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidServices {

    @Autowired
    private GraficaRepository repository;

    public List<Grafica> findAll(){
        List<Grafica> lista = repository.findAllByType("Covid-19");
        lista.stream()
                .sorted(Comparator.comparing(Grafica::getCreatedAt).reversed())
                .collect(Collectors.toList());
        return lista;
    }

    public List<Grafica> findByCategoryOrTitle(String title, String tipo){
        List<Grafica> lista = repository.findByTitleOrType(tipo,title);
        lista.stream().sorted(Comparator.comparing(Grafica::getCreatedAt).reversed()).collect(Collectors.toList());
        return lista;
    }
}
