package com.uo.app.exceptions.PublicationExceptions;

import org.springframework.http.HttpStatus;
import com.uo.app.exceptions.GeneralExceptions.HttpException;

public class PublicacionNoEncontrada extends HttpException{

    public PublicacionNoEncontrada(String id) {
        super(HttpStatus.NOT_FOUND, "Publicación no encontrada: ");
    }
}
