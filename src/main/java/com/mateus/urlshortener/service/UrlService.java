package com.mateus.urlshortener.service;

import com.mateus.urlshortener.domain.UrlModel;
import com.mateus.urlshortener.mapper.UrlMapper;
import com.mateus.urlshortener.dto.UrlDto;
import com.mateus.urlshortener.exception.UrlNotFoundException;
import com.mateus.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    UrlMapper urlMapper;

    public URL findByShortUri(String shortUri) {
        Optional<UrlModel> optionalUrlModel = urlRepository.findByShortUri(shortUri);
        requireOptionalIsNotEmpty(optionalUrlModel);
        return optionalUrlModel.get().getUrl();
    }

    public void deleteById(Long id) {
        Optional<UrlModel> optionalUrlModel = urlRepository.findById(id);
        requireOptionalIsNotEmpty(optionalUrlModel);
        urlRepository.delete(optionalUrlModel.get());
    }

    public UrlModel save(UrlDto urlDto) {
        UrlModel url = urlMapper.toUrlModel(urlDto);
        return urlRepository.save(url);
    }

    private void requireOptionalIsNotEmpty(Optional<UrlModel> optional) {
        if (optional.isEmpty())
            throw new UrlNotFoundException();
    }
}
