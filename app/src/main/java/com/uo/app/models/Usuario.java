package com.uo.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.uo.app.dto.UsuarioDTO;

@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;
    @NonNull
    private String nombre;
    @NonNull
    private String password;
    @NonNull
    private String role;

    public Usuario() {}

    public Usuario(String nombre, String password, String role) {
        this.nombre = nombre;
        this.password = password;
        this.role = role.toUpperCase();
    }

    public void actualizarDesdeDTO(UsuarioDTO dto) {
        if (dto.getNombre() != null) this.nombre = dto.getNombre();
        if (dto.getPassword() != null) this.password = dto.getPassword();
        if (dto.getRole() != null) this.role = dto.getRole();    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

}
