package com.example.TinyLinkApplication.Repository;

import com.example.TinyLinkApplication.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url,Long> {


    Url findByUrl(String originalUrl);

    Optional<Url> findByUrlCode(String urlCode);
}
