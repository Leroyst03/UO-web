package com.uo.app.controllers.Api;

import org.springframework.web.bind.annotation.RestController;
import com.uo.app.dto.PublicacionDTO;
import com.uo.app.models.Publicacion;
import com.uo.app.services.PublicacionService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/publicaciones")
public class PublicacionController {
    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    // GET libre o autenticado según config de SecurityConfig
    @GetMapping
    public ResponseEntity<List<Publicacion>> obtenerPublicaciones() {
        return ResponseEntity.ok(publicacionService.obtenerPublicaciones());
    }

    // Crear: alumno y profesor
    @PostMapping
    @PreAuthorize("hasAnyRole('ALUMNO','PROFESOR')")
    public ResponseEntity<Publicacion> crear(@RequestBody PublicacionDTO dto, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(publicacionService.guardarPublicacion(dto, auth));
    }

    // Actualizar: alumno y profesor (pero el servicio valida si el alumno solo edita lo suyo)
    @PutMapping
    @PreAuthorize("hasAnyRole('ALUMNO','PROFESOR')")    
    public ResponseEntity<Publicacion> actualizar(@RequestBody PublicacionDTO dto, Authentication auth) {
        return ResponseEntity.ok(publicacionService.actualizarPublicacion(dto, auth));
    }

    // Eliminar: solo profesor
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> eliminar(@PathVariable String id, Authentication auth) {
        publicacionService.eliminarPublicacion(id, auth);
        return ResponseEntity.noContent().build();
    }
}
