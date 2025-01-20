package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.entities.PD.PlanoDoDia;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanoDoDiaRepository extends JpaRepository<PlanoDoDia,String>, JpaSpecificationExecutor<PlanoDoDia> {

    @Modifying
    @Query(value = "UPDATE tb_planodia SET download = nextval('pd_download_count_seq') WHERE id = ?1" , nativeQuery = true)
    void setDownloded(String id);

    default List<PlanoDoDia> findAllByYear(String ano) {
        Specification<PlanoDoDia> specs = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ano"),ano);
        return findAll(specs);
    }

    default List<PlanoDoDia> findByTitle(String title){
        Specification<PlanoDoDia> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        if(!title.isEmpty() && title != null) {
             Specification<PlanoDoDia> titleLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
             specs = specs.and(titleLike);
        }
        return findAll(specs);
    }
}
