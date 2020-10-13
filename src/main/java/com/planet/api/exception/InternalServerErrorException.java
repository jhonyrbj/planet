package com.planet.api.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RestErrorException {
    public InternalServerErrorException(HttpStatus status) {
        super(status);
    }

    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
