package com.uo.app.controllers.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uo.app.dto.UsuarioDTO;
import com.uo.app.services.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class SignUp {
    private final UsuarioService usuarioService;

    public SignUp(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> postMethodName(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.crearUsuario(usuarioDTO);        
        return ResponseEntity.noContent().build();
    }

}
