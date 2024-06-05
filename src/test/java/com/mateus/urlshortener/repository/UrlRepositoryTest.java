package com.mateus.urlshortener.repository;

import com.mateus.urlshortener.domain.Url;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UrlRepositoryTest {
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    EntityManager entityManager;

    Url url;
    String shortUri;

    @BeforeEach @Transactional
    void setup() {
        url = new Url(null, "https://www.google.com");
        shortUri = url.getShortUri();
        entityManager.persist(url);
    }

    @Test @Transactional
    void findByShortUriSuccess() {
        Url urlFounded = urlRepository.findByshortUri(shortUri);
        assertEquals(url, urlFounded);
    }
}