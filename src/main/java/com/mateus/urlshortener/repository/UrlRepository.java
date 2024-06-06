package com.mateus.urlshortener.repository;

import com.mateus.urlshortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    public Optional<Url> findByShortUri(String shortUri);
}
