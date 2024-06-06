package com.mateus.urlshortener.service;

import com.mateus.urlshortener.mapper.UrlMapper;
import com.mateus.urlshortener.domain.Url;
import com.mateus.urlshortener.dto.UrlDto;
import com.mateus.urlshortener.exception.UrlNotFoundException;
import com.mateus.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class UrlServiceTest {
    @InjectMocks
    UrlService urlService;

    @Mock
    UrlRepository urlRepository;

    @Mock
    UrlMapper urlMapper;

    Url url;
    String shortUri;

    @BeforeEach
    void setup() {
        url = new Url(1L, "https://www.google.com");
        shortUri = url.getShortUri();
    }

    @Test
    @DisplayName("Should return the URL with the passed URI")
    void getUrlByShortUriSuccess() {
        when(urlRepository.findByShortUri(shortUri)).thenReturn(Optional.of(url));
        UrlDto urlFounded = urlService.findByShortUri(shortUri);

        verify(urlRepository).findByShortUri(shortUri);
        verifyNoMoreInteractions(urlRepository);
        assertEquals(url.getUrl(), urlFounded.url());
    }

    @Test
    @DisplayName("Should throw UrlNotFoundException when don't have any URL with the passed URI")
    void getUrlByShortUriThrowsException() {
        Optional<Url> optionalReturnedByRepository = Optional.empty();
        String uriToFind = "invalid0";
        when(urlRepository.findByShortUri(uriToFind))
                .thenReturn(optionalReturnedByRepository);

        assertThrows(UrlNotFoundException.class, () -> urlService.findByShortUri(uriToFind));
        verify(urlRepository).findByShortUri(uriToFind);
        verifyNoMoreInteractions(urlRepository);
    }

    @Test
    @DisplayName("Should delete a URL by id successfully")
    void deleteUrlByIdSuccess() {
        when(urlRepository.findById(1L))
                .thenReturn(Optional.of(url));
        urlService.deleteById(1L);

        verify(urlRepository).findById(1L);
        verify(urlRepository).delete(url);
        verifyNoMoreInteractions(urlRepository);
    }

    @Test
    @DisplayName("Should throw UrlNotFoundException when don't have any URL with the passed URI to delete")
    void deleteUrlByIdThrowsException() {
        Optional<Url> optionalReturnedByRepository = Optional.empty();
        when(urlRepository.findById(123L))
                .thenReturn(optionalReturnedByRepository);

        assertThrows(UrlNotFoundException.class, () -> urlService.deleteById(123L));
        verify(urlRepository).findById(123L);
        verifyNoMoreInteractions(urlRepository);
    }

    @Test
    @DisplayName("Should save a URL successfully")
    void saveUrlSuccess() {
        UrlDto urlDto = new UrlDto("https://www.google.com");
        when(urlMapper.toUrl(urlDto))
                .thenReturn(new Url(null, urlDto.url()));
        Url mappedUrl = urlMapper.toUrl(urlDto);
        Url urlToReturn = new Url(3L, urlDto.url());
        urlToReturn.setShortUri(mappedUrl.getShortUri());

        when(urlRepository.save(mappedUrl))
                .thenReturn(urlToReturn);

        Url savedUrl = urlService.save(urlDto);
        verify(urlRepository).save(mappedUrl);
        verifyNoMoreInteractions(urlRepository);
        assertEquals(urlDto.url(), savedUrl.getUrl());
    }
}