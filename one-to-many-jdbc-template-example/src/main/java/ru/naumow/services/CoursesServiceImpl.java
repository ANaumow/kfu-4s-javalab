package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumow.dto.CourseDto;
import ru.naumow.dto.LessonDto;
import ru.naumow.models.Course;
import ru.naumow.models.Lesson;
import ru.naumow.repositories.CoursesRepository;
import ru.naumow.repositories.LessonsRepository;

import java.util.ArrayList;
import java.util.List;

@Component("courseService")
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private LessonsRepository lessonsRepository;

    @Override
    public CourseDto getCourse(Long id) {
        Course course = coursesRepository.find(id).get();
        List<Lesson> lessons = lessonsRepository.findByCourseId(course.getId());

        List<LessonDto> lessonDtos = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDtos.add(new LessonDto(lesson.getId(), lesson.getName()));
        }
        return new CourseDto(course.getId(), course.getTitle(), lessonDtos);
    }

}
