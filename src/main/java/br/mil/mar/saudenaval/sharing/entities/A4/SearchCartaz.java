package br.mil.mar.saudenaval.sharing.entities.A4;

public class SearchCartaz {
    private String ano;
    private String title;

    public SearchCartaz() {
    }

    public SearchCartaz(String ano, String title) {
        this.ano = ano;
        this.title = title;
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
}
