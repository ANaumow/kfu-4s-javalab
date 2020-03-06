package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.naumow.models.Lesson;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String title;
    private List<LessonDto> lessonDtos;
}
