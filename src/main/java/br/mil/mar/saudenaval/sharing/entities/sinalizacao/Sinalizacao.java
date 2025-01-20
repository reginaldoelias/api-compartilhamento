package br.mil.mar.saudenaval.sharing.entities.sinalizacao;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_sinalizacao")
public class Sinalizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "altura")
    private String altura;

    @Column(name = "medida")
    private String medida;

    @Column(name = "file")
    private String file;

    @Column(name = "filename")
    private String filename;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "download")
    private Integer download;

    public Sinalizacao() {
    }

    public Sinalizacao(String title, String tipo, String codigo, String altura, String medida, String file, String filename, LocalDateTime createdAt, LocalDateTime updatedAt, Integer download) {
        this.title = title;
        this.tipo = tipo;
        this.codigo = codigo;
        this.altura = altura;
        this.medida = medida;
        this.file = file;
        this.filename = filename;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.download = download;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sinalizacao that = (Sinalizacao) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }
}
