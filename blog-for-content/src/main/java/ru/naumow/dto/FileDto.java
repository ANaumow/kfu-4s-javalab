package ru.naumow.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Value
@AllArgsConstructor
@Builder
public class FileDto {

    Long id;
    String contentType;
    Long   size;
    String localUrl;
    String publicUrl;

}
