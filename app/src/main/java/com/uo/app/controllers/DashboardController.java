package com.uo.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uo.app.services.PublicacionService;

@Controller
public class DashboardController {

    private final PublicacionService publicacionService;

    public DashboardController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        String rol = auth.getAuthorities().stream()
                         .map(a -> a.getAuthority())
                         .findFirst()
                         .orElse("ROLE_ALUMNO");

        String username = auth.getName(); // nombre del usuario autenticado

        if ("ROLE_PROFESOR".equals(rol)) {
            // Profesor ve todas las publicaciones
            model.addAttribute("publicaciones", publicacionService.obtenerPublicaciones());
        } else {
            // Alumno solo ve sus publicaciones
            model.addAttribute("publicaciones", publicacionService.obtenerPublicacionesPorUsuario(username));
        }

        model.addAttribute("rol", rol);
        return "dashboard";
    }
}
