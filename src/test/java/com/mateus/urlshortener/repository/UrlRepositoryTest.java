package com.mateus.urlshortener.repository;

import com.mateus.urlshortener.domain.UrlModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class UrlRepositoryTest {
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    EntityManager entityManager;

    UrlModel url;
    String shortUri;

    @BeforeEach @Transactional
    void setup() throws MalformedURLException {
        url = new UrlModel(null, new URL("https://www.google.com"));
        shortUri = url.getShortUri();
        entityManager.persist(url);
    }

    @Test @Transactional
    @DisplayName("Should return a Url with the passed URI as parameter")
    void findByShortUriSuccess() {
        Optional<UrlModel> urlOptional = urlRepository.findByShortUri(shortUri);
        UrlModel urlFounded = urlOptional.get();
        assertEquals(url, urlFounded);
    }
}