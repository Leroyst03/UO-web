package com.uo.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uo.app.services.PublicacionService;

@Controller
public class PageController {

    private final PublicacionService publicacionService;

    public PageController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    // Página principal con todas las publicaciones
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("publicaciones", publicacionService.obtenerPublicaciones());
        return "index";
    }

    // Página de detalle de una publicación
    @GetMapping("/publicacion/{id}")
    public String detalle(@PathVariable String id, Model model) {
        model.addAttribute("publicacion", publicacionService.obtenerPorId(id));
        return "detalle"; // Renderiza templates/detalle.html
    }

    // Página de login
    @GetMapping("/login")
    public String login() {
        return "login"; // Renderiza templates/login.html
    }

    // Página de registro
    @GetMapping("/usuarios/nuevo")
    @PreAuthorize("hasRole('PROFESOR')")
    public String nuevoUsuario() {
        return "usuarios-nuevo";
    }

}
