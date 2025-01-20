package br.mil.mar.saudenaval.sharing.entities.PD;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_planodia")
public class PlanoDoDia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "file")
    private String file;

    @Column(name = "filename")
    private String filename;

    @Column(name = "ano")
    private String ano;

    @Column(name = "texto")
    private String texto;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "download")
    private Integer download;

    @Column(name = "status")
    private String status;


    @Column(name = "enviados")
    private Integer enviados;

    public PlanoDoDia() {
    }

    public PlanoDoDia(String id, String title, String file, String filename, String ano, String texto, LocalDateTime createdAt, Integer download, String status, Integer enviados) {
        this.id = id;
        this.title = title;
        this.file = file;
        this.filename = filename;
        this.ano = ano;
        this.texto = texto;
        this.createdAt = createdAt;
        this.download = download;
        this.status = status;
        this.enviados = enviados;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getEnviados() {
        return enviados;
    }

    public void setEnviados(Integer enviados) {
        this.enviados = enviados;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlanoDoDia that = (PlanoDoDia) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }
}
