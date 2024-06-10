package com.mateus.urlshortener.service;

import com.mateus.urlshortener.domain.UrlModel;
import com.mateus.urlshortener.dto.UrlDto;
import com.mateus.urlshortener.exception.UrlNotFoundException;
import com.mateus.urlshortener.mapper.UrlMapper;
import com.mateus.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.MalformedURLException;
import java.net.URL;
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

    UrlModel urlModel;
    String shortUri;

    @BeforeEach
    void setup() throws MalformedURLException {
        urlModel = new UrlModel(1L, new URL("https://www.google.com"));
        shortUri = urlModel.getShortUri();
    }

    @Test
    @DisplayName("Should return the URL with the passed URI")
    void getUrlByShortUriSuccess() {
        when(urlRepository.findByShortUri(shortUri)).thenReturn(Optional.of(urlModel));
        URL urlFounded = urlService.findByShortUri(shortUri);

        verify(urlRepository).findByShortUri(shortUri);
        verifyNoMoreInteractions(urlRepository);
        assertEquals(urlModel.getUrl().toString(), urlFounded.toString());
    }

    @Test
    @DisplayName("Should throw UrlNotFoundException when don't have any URL with the passed URI")
    void getUrlByShortUriThrowsException() {
        Optional<UrlModel> optionalReturnedByRepository = Optional.empty();
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
                .thenReturn(Optional.of(urlModel));
        urlService.deleteById(1L);

        verify(urlRepository).findById(1L);
        verify(urlRepository).delete(urlModel);
        verifyNoMoreInteractions(urlRepository);
    }

    @Test
    @DisplayName("Should throw UrlNotFoundException when don't have any URL with the passed URI to delete")
    void deleteUrlByIdThrowsException() {
        Optional<UrlModel> optionalReturnedByRepository = Optional.empty();
        when(urlRepository.findById(123L))
                .thenReturn(optionalReturnedByRepository);

        assertThrows(UrlNotFoundException.class, () -> urlService.deleteById(123L));
        verify(urlRepository).findById(123L);
        verifyNoMoreInteractions(urlRepository);
    }

    @Test
    @DisplayName("Should save a URL successfully")
    void saveUrlSuccess() throws MalformedURLException {
        UrlDto urlDto = new UrlDto("https://www.google.com");
        when(urlMapper.toUrlModel(urlDto))
                .thenReturn(new UrlModel(null, new URL(urlDto.url())));
        UrlModel mappedUrl = urlMapper.toUrlModel(urlDto);
        UrlModel urlToReturn = new UrlModel(3L, new URL(urlDto.url()));
        urlToReturn.setShortUri(mappedUrl.getShortUri());

        when(urlRepository.save(mappedUrl))
                .thenReturn(urlToReturn);

        UrlModel savedUrl = urlService.save(urlDto);
        verify(urlRepository).save(mappedUrl);
        verifyNoMoreInteractions(urlRepository);
        assertEquals(urlDto.url(), savedUrl.getUrl().toString());
    }
}