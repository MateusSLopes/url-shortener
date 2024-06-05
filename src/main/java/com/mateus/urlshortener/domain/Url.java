package com.mateus.urlshortener.domain;

import com.mateus.urlshortener.util.UriGenerator;
import jakarta.persistence.*;

import java.util.Objects;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url1 = (Url) o;
        return Objects.equals(id, url1.id) && Objects.equals(url, url1.url) && Objects.equals(shortUri, url1.shortUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, shortUri);
    }
}
