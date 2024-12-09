package com.example.TinyLinkApplication.controller;

import com.example.TinyLinkApplication.entity.Url;
import com.example.TinyLinkApplication.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/shortner")
public class UrlController{
    @Autowired
    private UrlService service;



    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestParam String originalUrl){
        return ResponseEntity.ok(service.getShorternUrl(originalUrl));
    }

    @GetMapping("/{urlCode}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String urlCode) {
        Optional<Url> UrlOptional = service.getOriginalUrl(urlCode);
        if (UrlOptional.isPresent()) {
            Url url = UrlOptional.get();
            return ResponseEntity.status(302).location(URI.create(url.getOriginalUrl())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
