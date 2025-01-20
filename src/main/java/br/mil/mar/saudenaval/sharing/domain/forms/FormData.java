package br.mil.mar.saudenaval.sharing.domain.forms;

public class FormData {

    private String id;
    private String title;
    private String codigo;
    private String especialidade;


    public FormData() {
    }

    public FormData(String id, String title, String codigo, String especialidade) {
        this.id = id;
        this.title = title;
        this.codigo = codigo;
        this.especialidade = especialidade;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
