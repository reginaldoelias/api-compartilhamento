package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.config.Search;
import br.mil.mar.saudenaval.sharing.entities.institucional.Institucional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstitucionalRepository extends JpaRepository<Institucional,String>, JpaSpecificationExecutor<Institucional> {
    default List<Institucional> filterDocs(Search search){
        Specification<Institucional> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if(!search.getTipo().isEmpty() && search.getTipo() != null ){
            String tipo = search.getTipo().equals("anual")  ? "Relatorio Anual Consolidado" : "Produtividade Parcial";
            Specification<Institucional> tipoEquals = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipo"),tipo);
            specs = specs.and(tipoEquals);
        }

        if(!search.getAno().isEmpty() && search.getAno() != null){
            Specification<Institucional> anoEquals = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ano"),search.getAno());
            specs = specs.and(anoEquals);
        }

        return findAll(specs);
    }
    @Modifying
    @Query(value = "UPDATE tb_institucional SET download = nextval('relatorios_download_count_seq') WHERE id = ?1" , nativeQuery = true)
    void setDownloded(String id);

}
