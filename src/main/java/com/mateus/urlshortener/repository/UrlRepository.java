package com.mateus.urlshortener.repository;

import com.mateus.urlshortener.domain.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Long> {
    public Optional<UrlModel> findByShortUri(String shortUri);
}
