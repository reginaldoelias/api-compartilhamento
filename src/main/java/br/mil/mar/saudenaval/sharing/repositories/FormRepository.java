package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.domain.forms.FormData;
import br.mil.mar.saudenaval.sharing.entities.forms.Formularios;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormRepository extends JpaRepository<Formularios,String>, JpaSpecificationExecutor<Formularios> {

    @Modifying
    @Query(value = "UPDATE tb_formularios SET download = nextval('form_download_count_seq') WHERE id = ?1" , nativeQuery = true)
    void setDownloaded(String id);

    default List<Formularios> search(FormData data){
        Specification<Formularios> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if(!data.getTitle().isEmpty() && data.getTitle() != null){
            Specification<Formularios> titleLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%" + data.getTitle().toLowerCase() + "%");
            specs = specs.and(titleLike);
        }

        if(!data.getCodigo().isEmpty() && data.getCodigo() != null){
            Specification<Formularios> codeEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("codigo")),data.getCodigo().toLowerCase());
            specs = specs.and(codeEqual);
        }

        if(!data.getEspecialidade().isEmpty() && data.getEspecialidade() != null){
            Specification<Formularios> especEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("especialidade")),data.getEspecialidade().toLowerCase());
            specs = specs.and(especEqual);
        }

        return findAll(specs);
    }
}
