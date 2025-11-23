package com.uo.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.uo.app.dto.PublicacionDTO;

@Document(collection = "publicaciones")
public class Publicacion {
    @Id
    private String id;
    @NonNull
    private String titulo;
    @NonNull
    private String autor;
    @NonNull
    private String grupo;
    @NonNull
    private String texto;

    public Publicacion() {

    }

    public Publicacion(String titulo, String autor, String grupo, String texto) {
        this.titulo = titulo;
        this.autor = autor;
        this.grupo = grupo;
        this.texto = texto;
    }

    public void actualizarDesdeDTO(PublicacionDTO dto) {
        if (dto.getTitulo() != null) this.titulo = dto.getTitulo();
        if (dto.getGrupo() != null) this.grupo = dto.getGrupo();
        if (dto.getTexto() != null) this.texto = dto.getTexto();
    }

    public String getId() {
        return id;
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

    public String getAutor() {
        return this.autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }

   
} 