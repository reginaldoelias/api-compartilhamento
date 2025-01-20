package br.mil.mar.saudenaval.sharing.repositories;

import br.mil.mar.saudenaval.sharing.entities.SearchUser;
import br.mil.mar.saudenaval.sharing.entities.User;
import br.mil.mar.saudenaval.sharing.enums.Perfil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    UserDetails findByUsername(String username);

    @Override
    Optional<User> findById(String id);

    @Modifying
    @Query(value = "UPDATE tb_user SET password = ?2 WHERE username = ?1", nativeQuery = true)
    int updatePassword(String username, String password);

    @Modifying
    @Query(value = "UPDATE tb_user SET login_at = ?2, login_count = nextval('user_login_count_seq') WHERE username = ?1" , nativeQuery = true)
    void setLastLogin(String username, LocalDateTime dataHora);

    /*@Query(value = "SELECT * FROM tb_user WHERE username LIKE %:username% OR name LIKE %:name% OR nip = :nip OR om LIKE %:om% OR perfil = :perfil OR funcao = :funcao", nativeQuery = true)
    List<User> findAllContaining(@Param("username") String username, @Param("name") String name, @Param("nip") String nip, @Param("om") String om, @Param("perfil") String perfil, @Param("funcao") String funcao);
*/
    default List<User> findAllUsersFilter(SearchUser searchUser) {
        Specification<User> spec = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if(!searchUser.getPerfil().isEmpty() && searchUser.getPerfil() != null){
            Perfil perfil = Perfil.getValue(searchUser.getPerfil());
            Specification<User> perfilEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("perfil"),perfil);
            spec = spec.and(perfilEqual);
        }
        if (!searchUser.getNip().isEmpty() && searchUser.getNip() != null){
            Specification<User> nipEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nip"),searchUser.getNip());
            spec = spec.and(nipEqual);
        }

        if(!searchUser.getFuncao().isEmpty() && searchUser.getFuncao() != null){
            Specification<User> funcaoEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("funcao"),searchUser.getFuncao());
            spec = spec.and(funcaoEqual);
        }

        if(!searchUser.getUsername().isBlank() && searchUser.getUsername() != null){
            Specification<User> usernameLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),"%"+ searchUser.getUsername().toLowerCase() +"%");
            spec = spec.and(usernameLike);
        }

        if (!searchUser.getName().isBlank() && searchUser.getName() != null){
            Specification<User> nameLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+ searchUser.getName().toLowerCase()+"%");
            spec = spec.and(nameLike);
        }

        if (!searchUser.getOm().isBlank() && searchUser.getOm() != null){
            Specification<User> omLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("om")),"%"+searchUser.getOm().toLowerCase()+"%");
            Specification<User> siglaLike = (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("sigla")),"%"+searchUser.getOm().toLowerCase()+"%");
            Specification<User> omOrSiglaLike = Specification.anyOf(omLike,siglaLike);
            spec = spec.and(omOrSiglaLike);
        }

        return findAll(spec);
    }



}
