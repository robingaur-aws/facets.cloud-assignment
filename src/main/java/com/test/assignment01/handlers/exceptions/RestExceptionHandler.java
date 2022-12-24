package com.test.assignment01.handlers.exceptions;

import com.test.assignment01.entry.RestExceptionEntry;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({RestException.class, UndeclaredThrowableException.class})
    public ResponseEntity<RestExceptionEntry> handleRestException(RestException exception) {
        RestExceptionEntry entry = new RestExceptionEntry(exception.getError());
        return new ResponseEntity<>(entry, exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<RestExceptionEntry> handleException(Exception exception) {
        exception.printStackTrace();
        RestExceptionEntry entry = new RestExceptionEntry();
        entry.setError("Internal Server Error");
        return new ResponseEntity<>(entry, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
