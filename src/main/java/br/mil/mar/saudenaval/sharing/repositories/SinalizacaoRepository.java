package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.domain.sinalizacao.SinalData;
import br.mil.mar.saudenaval.sharing.entities.sinalizacao.Sinalizacao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SinalizacaoRepository extends JpaRepository<Sinalizacao,String>, JpaSpecificationExecutor<Sinalizacao> {
    @Modifying
    @Query(value = "UPDATE tb_sinalizacao SET download = nextval('sinal_download_count_seq') WHERE id = ?1" , nativeQuery = true)
    void setDownloaded(String id);


    default List<Sinalizacao> search(SinalData data){
        Specification<Sinalizacao> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (!data.getTipo().isEmpty() && data.getTipo() != null){
            Specification<Sinalizacao> tipoEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("tipo")),data.getTipo().toLowerCase());
            specs = specs.and(tipoEqual);
        }

        if (!data.getTitle().isEmpty() && data.getTitle() != null){
            Specification<Sinalizacao> titleLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%" + data.getTitle().toLowerCase() + "%");
            specs = specs.and(titleLike);
        }

        if(!data.getCodigo().isEmpty() && data.getCodigo() != null){
            Specification<Sinalizacao> codEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("codigo")),data.getCodigo().toLowerCase());
            specs = specs.and(codEqual);
        }

        return findAll(specs);
    }
}
