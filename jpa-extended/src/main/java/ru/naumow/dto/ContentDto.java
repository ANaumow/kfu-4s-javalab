package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.entities.Content;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentDto {

    private Long id;
    private String type;
    private String body;

    public static ContentDto from(Content content) {
        return ContentDto.builder()
                .id(content.getId())
                .body(content.getBody())
                .type(content.getType())
                .build();
    }

    public static List<ContentDto> from(List<Content> contents) {
        return contents.stream().map(ContentDto::from).collect(Collectors.toList());
    }

}
