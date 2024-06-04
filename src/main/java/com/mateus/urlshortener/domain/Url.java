package com.mateus.urlshortener.domain;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "tb_url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String url;

    @Column(unique = true)
    String shortUrl;

    public Url(Integer id, String url) {
        this.id = id;
        this.url = url;
        generateShortUrl();
    }

    public Url(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    private void generateShortUrl() {
        StringBuilder newUrl = new StringBuilder();
        Random generator = new Random();
        for (int i = 1; i <= 10; i++) {
            char newChar = (char) generator.nextInt(97, 123);
            newUrl.append(newChar);
        }
        setShortUrl(newUrl.toString());
    }
}
