package br.mil.mar.saudenaval.sharing.services.A4;

import java.time.LocalDateTime;
import java.util.Objects;

public class CartazDTO {
    private String id;
    private String title;
    private String poster;
    private String posterFilename;

    private String ano;
    private String doc;
    private String docFilename;

    private LocalDateTime createdAt;


    public CartazDTO() {
    }

    public CartazDTO(String id, String title, String poster, String posterFilename, String ano, String doc, String docFilename, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.posterFilename = posterFilename;
        this.ano = ano;
        this.doc = doc;
        this.docFilename = docFilename;
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDocFilename() {
        return docFilename;
    }

    public void setDocFilename(String docFilename) {
        this.docFilename = docFilename;
    }

    public String getPosterFilename() {
        return posterFilename;
    }

    public void setPosterFilename(String posterFilename) {
        this.posterFilename = posterFilename;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartazDTO cartazDTO = (CartazDTO) o;
        return Objects.equals(id, cartazDTO.id) && Objects.equals(createdAt, cartazDTO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }
}
