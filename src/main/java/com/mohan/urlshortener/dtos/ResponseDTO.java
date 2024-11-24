package com.mohan.urlshortener.dtos;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {

    Integer id;
    String url;
    String shortCode;
    Timestamp createdAt;
    Timestamp updatedAt;

}
