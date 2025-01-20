package br.mil.mar.saudenaval.sharing.entities.grafica;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_grafica")
public class Grafica {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "image")
    private String image;

    @Column(name = "image_url")
    private String imageUrl;


    @Column(name = "tipo")
    private String tipo;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "filename")
    private String filename;


    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "download")
    private Integer download;

    public Grafica() {
    }


    public Grafica(String title, String fileUrl, String image,String imageUrl, String tipo, String categoria ,String filename, LocalDateTime createdAt, Integer download) {
        this.title = title;
        this.fileUrl = fileUrl;
        this.image = image;
        this.imageUrl = imageUrl;
        this.tipo = tipo;
        this.categoria = categoria;
        this.filename = filename;
        this.createdAt = createdAt;
        this.download = download;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grafica grafica = (Grafica) o;
        return Objects.equals(id, grafica.id) && Objects.equals(createdAt, grafica.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }
}
