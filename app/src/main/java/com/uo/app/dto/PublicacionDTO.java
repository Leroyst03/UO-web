package com.uo.app.dto;

public class PublicacionDTO {
    private String id;
    private String titulo;
    private String autor;
    private String grupo;
    private String texto;

    public PublicacionDTO() {

    }

    public PublicacionDTO(String titulo, String grupo, String texto, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.grupo = grupo;
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
 