package com.planet.api.exception;

import org.springframework.http.HttpStatus;

public class RestErrorException extends RuntimeException implements CustomSecurityException {

    private HttpStatus status;

    public RestErrorException(HttpStatus status) {
        this.status = status;
    }

    public RestErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public RestErrorException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public RestErrorException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public RestErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
}
