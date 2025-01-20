package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.entities.grafica.Grafica;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GraficaRepository extends JpaRepository<Grafica,String>, JpaSpecificationExecutor<Grafica> {

    @Modifying
    @Query(value = "UPDATE tb_grafica SET download = nextval('grafica_download_count_seq') WHERE id = ?1" , nativeQuery = true)
    void setDownloaded(String id);

    default List<Grafica> findAllByType(String type){
        Specification<Grafica> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if(!type.isEmpty()){
            Specification<Grafica> typeEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("tipo")),type.toLowerCase());
            specs = specs.and(typeEqual);
        }

        return findAll(specs);
    }

    default List<Grafica> findByTitle(String title){
        Specification<Grafica> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        if (!title.isEmpty()){
            Specification<Grafica> titleLike = (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%" + title.toLowerCase() + "%");
            specs = specs.and(titleLike);
        }
        return findAll(specs);
    }

    default List<Grafica> findByTitleOrType(String tipo, String title){
        Specification<Grafica> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (!tipo.isEmpty()){
            Specification<Grafica> tipoEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("categoria")),tipo.toLowerCase());
            specs = specs.and(tipoEqual);
        }

        if(!title.isEmpty()){
            Specification<Grafica> titleLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%"+title.toLowerCase()+"%");
            specs = specs.and(titleLike);
        }
        specs = specs.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipo"),"Covid-19"));
        return findAll(specs);
    }
}
