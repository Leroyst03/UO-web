package com.uo.app.exceptions.UserExceptions;

import org.springframework.http.HttpStatus;
import com.uo.app.exceptions.GeneralExceptions.HttpException;

public class UsuarioNoEncontrado extends HttpException{
    public UsuarioNoEncontrado() {
        super(HttpStatus.NOT_FOUND, "Usuario no encontrado: ");
    }
}
