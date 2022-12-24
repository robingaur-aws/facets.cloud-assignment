package com.test.assignment01.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class RestException extends Exception {

    private final HttpStatus status;
    private final String error;

    public RestException(HttpStatus status) {
        this(status, null);
    }

    public RestException(HttpStatus status, String error) {
        this.status = status;
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
