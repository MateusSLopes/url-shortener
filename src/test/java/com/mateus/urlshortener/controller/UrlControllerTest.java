package com.mateus.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.urlshortener.domain.UrlModel;
import com.mateus.urlshortener.dto.UrlDto;
import com.mateus.urlshortener.repository.UrlRepository;
import com.mateus.urlshortener.service.UrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {
    @InjectMocks
    UrlController urlController;

    @Mock
    UrlService urlService;

    @Autowired
    ObjectMapper mapper;

    @Mock
    MockMvc mockMvc;
    UrlModel url;

    @Mock
    UrlRepository urlRepository;
    @BeforeEach
    void setup() throws MalformedURLException {
        mockMvc = MockMvcBuilders.standaloneSetup(urlController)
                .alwaysDo(print()).build();
        url = new UrlModel(1L, new URL("https://www.google.com/"));
    }

    @Test
    @DisplayName("Should redirect the user to the URL")
    void redirectByShortUriWithSuccess() throws Exception {
        when(urlService.findByShortUri(url.getShortUri()))
                .thenReturn(url.getUrl());
        mockMvc.perform(get("/find/{shortUri}", url.getShortUri()))
                .andExpect(redirectedUrl(url.getUrl().toString()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Should save a URL successfully")
    void saveWithSuccess() throws Exception{
        var urlDto = new UrlDto("https://www.google.com/");
        when(urlService.save(urlDto))
                .thenReturn(url);

        mockMvc.perform(post("/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(urlDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(mapper.writeValueAsString(url)));

        verify(urlService).save(urlDto);
        verifyNoMoreInteractions(urlService);
    }

    @Test
    @DisplayName("Should delete a URL successfully")
    void deleteByIdWithSuccess() throws Exception {
        doNothing().when(urlService).deleteById(1L);
        mockMvc.perform(delete("/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Url deleted successfully"));

        verify(urlService).deleteById(1L);
        verifyNoMoreInteractions(urlService);
    }
}