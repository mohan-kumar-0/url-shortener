package com.mohan.urlshortener.controllers;

import com.mohan.urlshortener.dtos.RequestDTO;
import com.mohan.urlshortener.dtos.ResponseDTO;
import com.mohan.urlshortener.dtos.URLStatsDTO;
import com.mohan.urlshortener.entities.ShortenedURL;
import com.mohan.urlshortener.services.URLServices;
import com.mohan.urlshortener.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class URLController {

    @Autowired
    private URLServices urlServices;

    @PostMapping("/shorten")
    public ResponseDTO shorten(@RequestBody RequestDTO request) {
        ShortenedURL shortenedURL = urlServices.shorten(request.getUrl());
        return new ResponseDTO(
                shortenedURL.getShortenedURL(),
                shortenedURL.getFullURL(),
                CommonUtils.toBase62(shortenedURL.getShortenedURL()),
                shortenedURL.getCreated(),
                shortenedURL.getLastUpdated()
        );
    }

    @GetMapping("/shorten/{id}")
    public ResponseDTO getShortenedURL(@PathVariable String id) {
        Optional<ShortenedURL> optionalShortenedURL = urlServices.getShortenedURL(CommonUtils.fromBase62(id));
        if (optionalShortenedURL.isPresent()) {
            ShortenedURL shortenedURL = optionalShortenedURL.get();
            shortenedURL.setAccessCount(shortenedURL.getAccessCount() + 1);
            shortenedURL.setLastAccessed(Timestamp.from(Instant.now()));
            urlServices.updateShortenedURL(shortenedURL.getShortenedURL(), shortenedURL.getFullURL());
            return new ResponseDTO(
                    shortenedURL.getShortenedURL(),
                    shortenedURL.getFullURL(),
                    CommonUtils.toBase62(shortenedURL.getShortenedURL()),
                    shortenedURL.getCreated(),
                    shortenedURL.getLastUpdated()
            );
        } else {
            return new ResponseDTO(-1, "URL not found", "-1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        }
    }

    @DeleteMapping("/shorten/{id}")
    public ResponseEntity<String> deleteShortenedURL(@PathVariable String id) {
        boolean isDeleted = urlServices.deleteShortenedURL(CommonUtils.fromBase62(id));
        if (isDeleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/shorten/{id}")
    public ResponseDTO updateShortenedURL(@PathVariable String id, @RequestBody RequestDTO request) {
        ShortenedURL updatedURL = urlServices.updateShortenedURL(CommonUtils.fromBase62(id), request.getUrl());
        if (updatedURL != null) {
            return new ResponseDTO(
                    updatedURL.getShortenedURL(),
                    updatedURL.getFullURL(),
                    CommonUtils.toBase62(updatedURL.getShortenedURL()),
                    updatedURL.getCreated(),
                    updatedURL.getLastUpdated()
            );
        } else {
            return new ResponseDTO(-1, "URL not found", "", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        }
    }

    @GetMapping("/shorten/{shortCode}/stats")
    public URLStatsDTO getURLStats(@PathVariable String shortCode) {
        Optional<ShortenedURL> shortenedURL = urlServices.getURLStats(shortCode);
        if (shortenedURL.isPresent()) {
            ShortenedURL url = shortenedURL.get();
            return new URLStatsDTO(
                    url.getShortenedURL(),
                    url.getFullURL(),
                    shortCode,
                    url.getCreated().toInstant(),
                    url.getLastUpdated().toInstant(),
                    url.getAccessCount()
            );
        } else {
            return null;
        }
    }

}
