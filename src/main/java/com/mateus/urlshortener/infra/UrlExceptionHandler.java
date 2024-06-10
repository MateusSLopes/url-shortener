package com.mateus.urlshortener.infra;

import com.mateus.urlshortener.exception.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.MalformedURLException;
import java.time.Instant;

@ControllerAdvice
public class UrlExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleUrlNotFoundException(UrlNotFoundException e) {
        var restErrorMessage = new RestErrorMessage(e.getMessage(), Instant.now(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(restErrorMessage.httpStatus()).body(restErrorMessage);
    }

    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<RestErrorMessage> handleMalformedURLException(MalformedURLException e) {
        var restErrorMessage = new RestErrorMessage(e.getMessage(), Instant.now(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(restErrorMessage.httpStatus()).body(restErrorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleException(Exception e) {
        var restErrorMessage = new RestErrorMessage(e.getMessage(), Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(restErrorMessage.httpStatus()).body(restErrorMessage);
    }
}
