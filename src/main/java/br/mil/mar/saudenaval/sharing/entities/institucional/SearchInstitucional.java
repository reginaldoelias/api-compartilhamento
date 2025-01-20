package br.mil.mar.saudenaval.sharing.entities.institucional;

public class SearchInstitucional {

    private String tipo;

    private String ano;

    public SearchInstitucional() {
    }

    public SearchInstitucional(String tipo, String ano) {
        this.tipo = tipo;
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
