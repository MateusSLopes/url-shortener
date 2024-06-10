package com.mateus.urlshortener.domain;

import com.mateus.urlshortener.util.UriGenerator;
import jakarta.persistence.*;

import java.net.URL;
import java.util.Objects;


@Entity
@Table(name = "tb_url")
public class UrlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    URL url;
    private static final UriGenerator uriGenerator = new UriGenerator();

    @Column(unique = true)
    String shortUri;

    public UrlModel(Long id, URL url) {
        this.id = id;
        this.url = url;
        setShortUri(uriGenerator.generateUri());
    }

    public UrlModel(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
        setShortUri(uriGenerator.generateUri());
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
        UrlModel urlModel = (UrlModel) o;
        return Objects.equals(id, urlModel.id) && Objects.equals(url, urlModel.url) && Objects.equals(shortUri, urlModel.shortUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, shortUri);
    }
}
