package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.entities.videos.Videos;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideosRepository extends JpaRepository<Videos,String>, JpaSpecificationExecutor<Videos> {
    default List<Videos> findAllByYear(String ano) {
        Specification<Videos> specs = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ano"),ano);
        return findAll(specs);
    }

    default List<Videos> findByTitle(String title){
        Specification<Videos> specs = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%" + title.toLowerCase() + "%");
        return findAll(specs);
    }

    @Modifying
    @Query(value = "UPDATE tb_videos SET download = nextval('videos_download_count_seq') WHERE id = ?1" , nativeQuery = true)
    void setDownloaded(String id);
}
