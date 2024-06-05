package com.mateus.urlshortener.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UriGeneratorTest {
    UriGenerator uriGenerator;

    @BeforeEach
    void setup() {
        uriGenerator = new UriGenerator();
    }

    @Test
    void generateShortUrlSuccess() {
        String shortUri = uriGenerator.generateUri();
        assertEquals(shortUri.length(), 10);
    }
}