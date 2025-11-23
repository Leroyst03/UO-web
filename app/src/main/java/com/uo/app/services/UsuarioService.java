package com.uo.app.services;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uo.app.dto.UsuarioDTO;
import com.uo.app.exceptions.UserExceptions.UsuarioNoEncontrado;
import com.uo.app.exceptions.UserExceptions.UsuarioEnUsoActualmente;
import com.uo.app.models.Usuario;
import com.uo.app.repository.UsuarioRepository;
import com.uo.app.security.JwtUtil;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void crearUsuario(UsuarioDTO usuarioDTO) {
        if(usuarioRepository.existsByNombre(usuarioDTO.getNombre())) {
            throw new UsuarioEnUsoActualmente(usuarioDTO.getNombre());
        }
        usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        if(usuarioDTO.getRole() == null || usuarioDTO.getRole().isBlank()) {
            usuarioDTO.setRole("ALUMNO");
        }

        Usuario usuario = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getPassword(), 
            usuarioDTO.getRole());
        usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id).
            orElseThrow(() -> new UsuarioNoEncontrado());
        return usuario;
    }

    public void actualizarPassword(String id, String password) {
        Usuario usuario = usuarioRepository.findById(id).
            orElseThrow(() -> new UsuarioNoEncontrado());
        usuario.setPassword(password);
    }

    public void borrarUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id). 
            orElseThrow(() -> new UsuarioNoEncontrado());
        usuarioRepository.delete(usuario);
    }

    public String log(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByNombre(usuarioDTO.getNombre()).
        orElseThrow(() -> new UsuarioNoEncontrado());

        if(passwordEncoder.matches(usuarioDTO.getPassword(), usuario.getPassword())) {
           return jwtUtil.generarToken(usuario.getNombre(), usuario.getRole());
        }

        return "";
    }

}
