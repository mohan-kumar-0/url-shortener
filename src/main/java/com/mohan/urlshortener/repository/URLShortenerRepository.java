package com.mohan.urlshortener.repository;

import com.mohan.urlshortener.entities.ShortenedURL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLShortenerRepository extends JpaRepository<ShortenedURL, Integer> {

    Optional<ShortenedURL> findById(Integer id);

    void deleteById(Integer id);

    boolean existsById(Integer id);
}
