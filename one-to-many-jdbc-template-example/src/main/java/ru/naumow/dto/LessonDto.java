package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessonDto {
    private Long id;
    private String name;
}
