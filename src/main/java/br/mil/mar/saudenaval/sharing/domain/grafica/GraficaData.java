package br.mil.mar.saudenaval.sharing.domain.grafica;

public class GraficaData {

    private String id;
    private String title;
    private String tipo;
    private String categoria;

    public GraficaData() {
    }

    public GraficaData(String id, String title, String tipo,String categoria) {
        this.id = id;
        this.title = title;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
