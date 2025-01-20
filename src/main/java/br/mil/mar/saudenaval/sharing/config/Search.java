package br.mil.mar.saudenaval.sharing.config;

public class Search {
    private String ano;
    private String title;
    private String tipo;

    public Search() {
    }

    public Search(String ano, String title) {
        this.ano = ano;
        this.title = title;
    }

    public Search(String ano, String title, String tipo) {
        this.ano = ano;
        this.title = title;
        this.tipo = tipo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
