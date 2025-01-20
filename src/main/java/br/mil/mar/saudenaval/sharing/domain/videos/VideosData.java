package br.mil.mar.saudenaval.sharing.domain.videos;

public class VideosData {
    private String id;
    private String title;
    private String ano;


    public VideosData() {
    }

    public VideosData(String id,String title, String ano) {
        this.id = id;
        this.title = title;
        this.ano = ano;
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

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
