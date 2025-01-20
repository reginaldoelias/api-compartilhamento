package br.mil.mar.saudenaval.sharing.entities;

public class SearchUser {

    private String username;
    private String name;
    private String nip;
    private String om;
    private String perfil;
    private String funcao;

    public SearchUser() {
    }

    public SearchUser(String username, String name, String nip, String om, String perfil, String funcao) {
        this.username = username;
        this.name = name;
        this.nip = nip;
        this.om = om;
        this.perfil = perfil;
        this.funcao = funcao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getOm() {
        return om;
    }

    public void setOm(String om) {
        this.om = om;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
