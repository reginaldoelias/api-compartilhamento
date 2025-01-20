package br.mil.mar.saudenaval.sharing.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Perfil {
    ADMINISTRADOR("Administrador"),
    FORMULARIO("Formulário"),
    SINALIZACAO("Sinalização"),
    GESTOR("Gestor");

    private String profile;

    Perfil(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public static Perfil getValue(String perfil){
        return Arrays.stream(values())
                .filter(p-> p.profile.equals(perfil))
                .findFirst().orElse(null);
    }


    public GrantedAuthority toGrantedAuthority(){
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }

    public static List<GrantedAuthority> toGrantedAuthorities(List<Perfil> perfis){
        return perfis.stream().map(Perfil::toGrantedAuthority)
                .collect(Collectors.toList());
    }
}
