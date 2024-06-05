package com.mateus.urlshortener.util;

import java.util.Random;

public class UriGenerator {
    public String generateUri() {
        StringBuilder newUri = new StringBuilder();
        Random generator = new Random();
        for (int i = 1; i <= 10; i++) {
            char newChar = (char) generator.nextInt(97, 123);
            newUri.append(newChar);
        }
        return newUri.toString();
    }
}
