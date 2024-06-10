package com.mateus.urlshortener.infra;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record RestErrorMessage (String message, Instant moment, HttpStatus httpStatus) {}