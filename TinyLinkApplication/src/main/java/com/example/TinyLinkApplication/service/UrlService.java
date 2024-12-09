package com.example.TinyLinkApplication.service;

import com.example.TinyLinkApplication.Repository.UrlRepository;
import com.example.TinyLinkApplication.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Random;

public class UrlService {
    private static final String APHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static  final int  URL_CODE_LENGTH = 6;
    private String genrateUrl(){
        StringBuilder urlCode = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<URL_CODE_LENGTH; i++){
            int idx = random.nextInt(APHANUMERIC_STRING.length());
            urlCode.append(APHANUMERIC_STRING.charAt(idx));
        }
        return urlCode.toString();
    }

    @Autowired
    private UrlRepository repository;
    public Url getShorternUrl(String originalUrl){
        String urlCode = genrateUrl();
        return repository.save(Url.builder().originalUrl(originalUrl).urlCode(urlCode).build());
    }
    public Optional<Url> getOriginalUrl(String urlCode){
        return repository.findByUrlCode(urlCode);
    }
}