package com.br.catesysapi.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends RuntimeException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ValidationException(String message) {
        super(message);
    }

}
