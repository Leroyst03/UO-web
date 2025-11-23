package com.uo.app.exceptions.UserExceptions;

import org.springframework.http.HttpStatus;

import com.uo.app.exceptions.GeneralExceptions.HttpException;

public class UsuarioEnUsoActualmente extends HttpException{
    public UsuarioEnUsoActualmente(String nombre) {
        super(HttpStatus.BAD_REQUEST, "El usuario ya se encuentra en uso: " + nombre);
    }
}
