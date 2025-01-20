package br.mil.mar.saudenaval.sharing.domain.A4;

public class PapelOficioData {

    private String id;
    private String title;
    private String ano;

    public PapelOficioData() {
    }

    public PapelOficioData(String id, String title, String ano) {
        this.id = id;
        this.title = title;
        this.ano = ano;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
