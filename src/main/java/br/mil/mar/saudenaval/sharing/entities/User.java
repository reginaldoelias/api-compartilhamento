package br.mil.mar.saudenaval.sharing.entities;


import br.mil.mar.saudenaval.sharing.enums.Perfil;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "tb_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String status;

    @Column
    private String name;

    @Column
    private String nip;

    @Column
    private String posto;

    @Column
    private String sigla;

    @Column
    private String om;

    @Column
    private String funcao;

    @Column
    private Boolean pd;

    @Column
    private String vitrine;

    @Column
    private String tel;

    @Column
    private String cel;

    @Column
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;


    @Column(name = "login_at")
    private LocalDateTime lastLogin;

    @Column(name = "login_count")
    private Integer loginCount;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getOm() {
        return om;
    }

    public void setOm(String om) {
        this.om = om;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Boolean getPd() {
        return pd;
    }

    public void setPd(Boolean pd) {
        this.pd = pd;
    }

    public String getVitrine() {
        return vitrine;
    }

    public void setVitrine(String vitrine) {
        this.vitrine = vitrine;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public Perfil getPerfil() {

        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        switch (this.perfil){
            case ADMINISTRADOR -> {
                return List.of(new SimpleGrantedAuthority("ROLE_" + Perfil.ADMINISTRADOR.name()),new SimpleGrantedAuthority("ROLE_" + Perfil.GESTOR.name()),new SimpleGrantedAuthority("ROLE_" + Perfil.FORMULARIO.name()),new SimpleGrantedAuthority("ROLE_" + Perfil.SINALIZACAO.name()));
            }
            case FORMULARIO -> {
                return List.of(new SimpleGrantedAuthority("ROLE_" + Perfil.FORMULARIO.name()));
            }
            case SINALIZACAO -> {
                return List.of(new SimpleGrantedAuthority("ROLE_" + Perfil.SINALIZACAO.name()));
            }
            case GESTOR -> {
                return List.of(new SimpleGrantedAuthority("ROLE_" + Perfil.FORMULARIO.name()), new SimpleGrantedAuthority("ROLE_" + Perfil.SINALIZACAO.name()));
            }
            default -> {
                return List.of( new SimpleGrantedAuthority("ROLE_USER"));
            }
        }

        /*if (this.perfil == Perfil.ADMINISTRADOR){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of( new SimpleGrantedAuthority("ROLE_USER"));
        }*/

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
