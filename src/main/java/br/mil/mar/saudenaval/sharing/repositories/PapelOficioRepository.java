package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.entities.A4.PapelOficio;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PapelOficioRepository extends JpaRepository<PapelOficio,String>, JpaSpecificationExecutor<PapelOficio> {
    @Modifying
    @Query(value = "UPDATE tb_a4 SET download = nextval('a4_download_count_seq') WHERE id = ?1" , nativeQuery = true)
    void setDownloded(String id);

    default List<PapelOficio> findByYearOrTitle(String year,String title){
        Specification<PapelOficio> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if(!year.isBlank()){
            Specification<PapelOficio> anoEquals = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ano"),year);
            specs = specs.and(anoEquals);
        }

        if(!title.isBlank()){
            Specification<PapelOficio> titleLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%"+title.toLowerCase()+"%");
            specs = specs.and(titleLike);
        }
    return findAll(specs);

    }
}
