package com.mateus.urlshortener.domain;

import com.mateus.urlshortener.util.UriGenerator;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String url;
    private static final UriGenerator uriGenerator = new UriGenerator();

    @Column(unique = true)
    String shortUri;

    public Url(Integer id, String url) {
        this.id = id;
        this.url = url;
        setShortUri(uriGenerator.generateUri());
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

    public String getShortUri() {
        return shortUri;
    }

    public void setShortUri(String shortUrl) {
        this.shortUri = shortUrl;
    }

}
