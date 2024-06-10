package com.mateus.urlshortener.controller;

import com.mateus.urlshortener.domain.UrlModel;
import com.mateus.urlshortener.dto.UrlDto;
import com.mateus.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URL;

@RestController
public class UrlController {
    @Autowired
    UrlService urlService;

    @GetMapping("/{shortUri}")
    public RedirectView redirectByShortUri(@PathVariable String shortUri) {
        URL url = urlService.findByShortUri(shortUri);
        return new RedirectView(url.toString());
    }

    @PostMapping
    public ResponseEntity<UrlModel> saveUrl(@RequestBody UrlDto urlDto) {
        UrlModel urlModel = urlService.save(urlDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(urlModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUrlById(@PathVariable Long id) {
        urlService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Url deleted successfully");
    }
}
