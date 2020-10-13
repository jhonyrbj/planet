package com.planet.api.exception;

import org.springframework.http.HttpStatus;

public interface CustomSecurityException {

    HttpStatus getStatus();
    String getMessage();
}
