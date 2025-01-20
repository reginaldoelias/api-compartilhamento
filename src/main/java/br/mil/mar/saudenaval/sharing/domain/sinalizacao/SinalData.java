package br.mil.mar.saudenaval.sharing.domain.sinalizacao;

public class SinalData {

    private String id;
    private String title;
    private String tipo;
    private String codigo;
    private String altura;
    private String medida;


    public SinalData() {
    }

    public SinalData(String id, String title, String tipo, String codigo, String altura, String medida) {
        this.id = id;
        this.title = title;
        this.tipo = tipo;
        this.codigo = codigo;
        this.altura = altura;
        this.medida = medida;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
}
