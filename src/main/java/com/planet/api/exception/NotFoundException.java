package com.planet.api.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RestErrorException {
    public NotFoundException(HttpStatus status) {
        super(status);
    }

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
