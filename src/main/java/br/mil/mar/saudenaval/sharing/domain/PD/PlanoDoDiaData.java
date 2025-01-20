package br.mil.mar.saudenaval.sharing.domain.PD;

import java.util.Objects;

public class PlanoDoDiaData {

    private String id;
    private String title;
    private String ano;
    private String texto;
    public PlanoDoDiaData() {
    }

    public PlanoDoDiaData(String id, String title, String ano, String texto) {
        this.id = id;
        this.title = title;
        this.ano = ano;
        this.texto = texto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlanoDoDiaData that = (PlanoDoDiaData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
