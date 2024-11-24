package com.mohan.urlshortener.services;

import com.mohan.urlshortener.entities.ShortenedURL;
import com.mohan.urlshortener.repository.URLShortenerRepository;
import com.mohan.urlshortener.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class URLServiceImpl implements URLServices {

    @Autowired
    private URLShortenerRepository repository;

    @Override
    public ShortenedURL shorten(String url) {
        ShortenedURL shortenedURL = new ShortenedURL();
        shortenedURL.setFullURL(url);
        shortenedURL.setAccessCount(0);
        shortenedURL.setCreated(new Timestamp(System.currentTimeMillis()));
        shortenedURL.setLastAccessed(new Timestamp(System.currentTimeMillis()));
        shortenedURL.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        return repository.save(shortenedURL);
    }

    @Override
    public Optional<ShortenedURL> getShortenedURL(Integer shortenedKey) {
        return repository.findById(shortenedKey);
    }

    @Override
    public boolean deleteShortenedURL(Integer shortenedKey) {
        if (repository.existsById(shortenedKey)) {
            repository.deleteById(shortenedKey);
            return true;
        }
        return false;
    }

    @Override
    public ShortenedURL updateShortenedURL(Integer shortenedKey, String newFullURL) {
        Optional<ShortenedURL> optionalShortenedURL = repository.findById(shortenedKey);
        if (optionalShortenedURL.isPresent()) {
            ShortenedURL shortenedURL = optionalShortenedURL.get();
            shortenedURL.setFullURL(newFullURL);
            shortenedURL.setLastUpdated(new Timestamp(System.currentTimeMillis()));
            return repository.save(shortenedURL);
        }
        return null;
    }

    @Override
    public Optional<ShortenedURL> getURLStats(String shortCode) {
        Integer id = CommonUtils.fromBase62(shortCode);
        return repository.findById(id);
    }
}
