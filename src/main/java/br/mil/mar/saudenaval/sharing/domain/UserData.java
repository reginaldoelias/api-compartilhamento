package br.mil.mar.saudenaval.sharing.domain;

import br.mil.mar.saudenaval.sharing.entities.User;
import br.mil.mar.saudenaval.sharing.enums.Perfil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserData {
    private String id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String status;
    private String name;
    private String nip;
    private String posto;
    private String sigla;
    private String om;
    private String funcao;
    private String pd;
    private String vitrine;
    private String tel;
    private String cel;

    public UserData() {
    }

    public UserData(String id, String username, String password, String role, String email, String status, String name, String nip, String posto, String sigla, String om, String funcao, String pd, String vitrine, String tel, String cel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.status = status;
        this.name = name;
        this.nip = nip;
        this.posto = posto;
        this.sigla = sigla;
        this.om = om;
        this.funcao = funcao;
        this.pd = pd;
        this.vitrine = vitrine;
        this.tel = tel;
        this.cel = cel;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getNip() {
        return nip;
    }

    public String getPosto() {
        return posto;
    }

    public String getSigla() {
        return sigla;
    }

    public String getOm() {
        return om;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getPd() {
        return pd;
    }

    public String getVitrine() {
        return vitrine;
    }

    public String getTel() {
        return tel;
    }

    public String getCel() {
        return cel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static User createRegister(UserData userData){
        String encryptedPassword = new BCryptPasswordEncoder().encode(userData.getPassword());
        Boolean pd = userData.getPd() != null;;
        User user = new User();
        user.setEmail(userData.getEmail());
        user.setUsername(userData.getUsername());
        user.setPassword(encryptedPassword);
        user.setStatus(userData.getStatus());
        user.setName(userData.getName());
        user.setNip(userData.getNip());
        user.setPosto(userData.getPosto());
        user.setSigla(userData.getSigla());
        user.setOm(userData.getOm());
        user.setFuncao(userData.getFuncao());
        user.setPd(pd);
        user.setVitrine(userData.getVitrine());
        user.setTel(userData.getTel());
        user.setCel(userData.getCel());
        user.setPerfil(Perfil.getValue(userData.role));
        return user;
    }
}
