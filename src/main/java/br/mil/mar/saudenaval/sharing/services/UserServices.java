package br.mil.mar.saudenaval.sharing.services;

import br.mil.mar.saudenaval.sharing.domain.UserData;
import br.mil.mar.saudenaval.sharing.entities.SearchUser;
import br.mil.mar.saudenaval.sharing.entities.User;
import br.mil.mar.saudenaval.sharing.enums.Perfil;
import br.mil.mar.saudenaval.sharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository repository;


    @Transactional
    public int updatePassword(String username,String password){
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        return repository.updatePassword(username,encryptedPassword);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

public User findById(String id){
        var user = repository.findById(id);
        return user.orElse(null);
    }

   

    public User updateUser(UserData userData){
        String encryptedPassword = new BCryptPasswordEncoder().encode(userData.getPassword());

        User usuario = repository.findById(userData.getId()).orElse(null);

        Boolean pd = userData.getPd() != null;

        if (usuario != null) {
            usuario.setEmail(userData.getEmail());
            usuario.setUsername(userData.getUsername());
            usuario.setPassword(encryptedPassword);
            usuario.setStatus(userData.getStatus());
            usuario.setName(userData.getName());
            usuario.setNip(userData.getNip());
            usuario.setPosto(userData.getPosto());
            usuario.setSigla(userData.getSigla());
            usuario.setOm(userData.getOm());
            usuario.setFuncao(userData.getFuncao());
            usuario.setPd(pd);
            usuario.setVitrine(userData.getVitrine());
            usuario.setTel(userData.getTel());
            usuario.setCel(userData.getCel());
            usuario.setPerfil(Perfil.getValue(userData.getRole()));
            repository.save(usuario);
        }
        return repository.findById(userData.getId()).orElse(null);
    }

    public List<User> filterUsers(SearchUser search){
        return  repository.findAllUsersFilter(search);
    }

    public void removeUser(String id){
       repository.deleteById(id);
    }

    @Transactional
    public void setLastLogin(String username, LocalDateTime dataHora){
        repository.setLastLogin(username,dataHora);
    }


}
