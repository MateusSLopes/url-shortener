package com.mateus.urlshortener.service;

import com.mateus.urlshortener.mapper.UrlMapper;
import com.mateus.urlshortener.domain.Url;
import com.mateus.urlshortener.dto.UrlDto;
import com.mateus.urlshortener.exception.UrlNotFoundException;
import com.mateus.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    UrlMapper urlMapper;

    public UrlDto findByShortUri(String shortUri) {
        Optional<Url> optionalUrl = urlRepository.findByShortUri(shortUri);
        requireOptionalIsNotEmpty(optionalUrl);
        return new UrlDto(optionalUrl.get().getUrl());
    }

    public void deleteById(Long id) {
        Optional<Url> optionalUrl = urlRepository.findById(id);
        requireOptionalIsNotEmpty(optionalUrl);
        urlRepository.delete(optionalUrl.get());
    }

    public Url save(UrlDto urlDto) {
        Url url = urlMapper.toUrl(urlDto);
        return urlRepository.save(url);
    }

    private void requireOptionalIsNotEmpty(Optional<Url> optional) {
        if (optional.isEmpty())
            throw new UrlNotFoundException();
    }
}
