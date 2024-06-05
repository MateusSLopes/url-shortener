package com.mateus.urlshortener.repository;

import com.mateus.urlshortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Integer> {
    public Url findByshortUri(String shortUri);
}
