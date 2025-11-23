package com.uo.app.exceptions.GeneralExceptions;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException{
    private final HttpStatus status;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    @Override
    public String toString() {
        return "HttpException{" +
                "status=" + status +
                ", message='" + getMessage() + '\'' +
                '}';
    }

    public HttpStatus getStatus() {
        return status;
    }

}
