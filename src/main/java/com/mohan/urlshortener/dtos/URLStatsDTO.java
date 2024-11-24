package com.mohan.urlshortener.dtos;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class URLStatsDTO {

    private int id;
    private String url;
    private String shortCode;
    private Instant createdAt;
    private Instant updatedAt;
    private int accessCount;

}
