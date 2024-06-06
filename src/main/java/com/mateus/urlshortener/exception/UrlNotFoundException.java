package com.mateus.urlshortener.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException() {
        super("URL not found!");
    }

    public UrlNotFoundException(String msg) {
        super(msg);
    }
}
