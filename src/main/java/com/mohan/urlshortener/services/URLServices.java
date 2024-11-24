package com.mohan.urlshortener.services;

import com.mohan.urlshortener.entities.ShortenedURL;
import java.util.Optional;

public interface URLServices {

    ShortenedURL shorten(String url);

    Optional<ShortenedURL> getShortenedURL(Integer shortenedKey);

    boolean deleteShortenedURL(Integer shortenedKey);

    ShortenedURL updateShortenedURL(Integer shortenedKey, String newFullURL);

    Optional<ShortenedURL> getURLStats(String shortCode);
}
