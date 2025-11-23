package com.uo.app.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.uo.app.dto.PublicacionDTO;
import com.uo.app.exceptions.PublicationExceptions.PublicacionNoEncontrada;
import com.uo.app.exceptions.GeneralExceptions.AccesoDenegadoException;
import com.uo.app.models.Publicacion;
import com.uo.app.repository.PublicacionRepository;

@Service
public class PublicacionService {
    private final PublicacionRepository publicacionRepository;

    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    // Crear publicación: cualquier usuario autenticado (profesor o alumno)
    public Publicacion guardarPublicacion(PublicacionDTO dto, Authentication auth) {
        Publicacion publicacion = new Publicacion(
            dto.getTitulo(),
            auth.getName(), // el autor es el usuario autenticado
            dto.getGrupo(),
            dto.getTexto()
        );
        return publicacionRepository.save(publicacion);
    }

    // Obtener todas las publicaciones: libre o autenticado según config de SecurityConfig
    public List<Publicacion> obtenerPublicaciones() {
        return publicacionRepository.findAll();
    }

    // Eliminar publicación: solo profesor
    public void eliminarPublicacion(String id, Authentication auth) {
        Publicacion publicacion = publicacionRepository.findById(id)
            .orElseThrow(() -> new PublicacionNoEncontrada(id));

        boolean esProfesor = auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESOR"));

        if (!esProfesor) {
            throw new AccesoDenegadoException("Solo los profesores pueden eliminar publicaciones");
        }

        publicacionRepository.delete(publicacion);
    }

    // Actualizar publicación: profesor puede editar cualquier registro, alumno solo el suyo
    public Publicacion actualizarPublicacion(PublicacionDTO dto, Authentication auth) {
        Publicacion publicacion = publicacionRepository.findById(dto.getId())
            .orElseThrow(() -> new PublicacionNoEncontrada(dto.getId()));

        boolean esProfesor = auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESOR"));

        if (esProfesor || publicacion.getAutor().equals(auth.getName())) {
            publicacion.actualizarDesdeDTO(dto);
            return publicacionRepository.save(publicacion);
        }

        throw new AccesoDenegadoException("No puedes editar esta publicación");
    }

    public Publicacion obtenerPorId(String id) {
        Publicacion publicacion = publicacionRepository.findById(id). 
            orElseThrow(() -> new PublicacionNoEncontrada(id));
        return publicacion;
    }

    public List<Publicacion> obtenerPublicacionesPorUsuario(String nombreUsuario) {
        return publicacionRepository.findByAutor(nombreUsuario);
    }

}   
 