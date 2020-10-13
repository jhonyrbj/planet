package com.planet.api.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RestErrorException {
    public BadRequestException(HttpStatus status) {
        super(status);
    }

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
