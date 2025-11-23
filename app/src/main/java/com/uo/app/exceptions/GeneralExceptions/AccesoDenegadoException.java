package com.uo.app.exceptions.GeneralExceptions;

import org.springframework.http.HttpStatus;

public class AccesoDenegadoException extends HttpException {
    
    public AccesoDenegadoException() {
        super(HttpStatus.FORBIDDEN, "Acceso denegado: no tienes permisos para realizar esta acción");
    }

    public AccesoDenegadoException(String detalle) {
        super(HttpStatus.FORBIDDEN, detalle);
    }
}
