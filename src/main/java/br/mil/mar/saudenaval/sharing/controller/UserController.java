package br.mil.mar.saudenaval.sharing.controller;

import br.mil.mar.saudenaval.sharing.domain.LoginResponseToken;
import br.mil.mar.saudenaval.sharing.domain.UserData;
import br.mil.mar.saudenaval.sharing.entities.Login;
import br.mil.mar.saudenaval.sharing.entities.SearchUser;
import br.mil.mar.saudenaval.sharing.entities.User;
import br.mil.mar.saudenaval.sharing.repositories.UserRepository;
import br.mil.mar.saudenaval.sharing.services.AuthorizationService;
import br.mil.mar.saudenaval.sharing.services.TokenService;
import br.mil.mar.saudenaval.sharing.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Login login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        //auth.isAuthenticated() -> se autenticou atualizar banco de dados ultimo login
        if(auth.isAuthenticated()){
            Instant instant = Instant.now();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT-3"));
            userServices.setLastLogin(login.getUsername(), localDateTime);
        }
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(LoginResponseToken.showToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserData userData){

        if (authorizationService.loadUserByUsername(userData.getUsername()) != null){
            return ResponseEntity.badRequest().build();
        }else{
            User user = UserData.createRegister(userData);
            this.repository.save(user);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestBody Login login){
       var update =  userServices.updatePassword(login.getUsername(), login.getPassword());
       if (update == 0){
           return  ResponseEntity.badRequest().build();
       }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lists")
    public ResponseEntity<List<User>> getAllUsers(){
       // System.out.print("Token: "+token);
        List<User> users = userServices.getAllUsers();
        return ResponseEntity.ok().body(users);
    }



    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserData userData){

        User updatedUser  = userServices.updateUser(userData);

        return ResponseEntity.ok().body(updatedUser);
    }

    @PostMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestBody SearchUser searchUser){

        List<User> listUser  = userServices.filterUsers(searchUser);

        return ResponseEntity.ok().body(listUser);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeUser(@PathVariable String id){
        //System.out.println(id);
        userServices.removeUser(id);
        if(id == null || id.isBlank()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
