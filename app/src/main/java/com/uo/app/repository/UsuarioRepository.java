package com.uo.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uo.app.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    public Optional<Usuario> findByNombre(String nombre);
    public Boolean existsByNombre(String nombre);
}
