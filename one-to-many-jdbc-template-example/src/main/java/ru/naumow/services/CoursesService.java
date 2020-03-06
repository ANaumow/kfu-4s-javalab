package ru.naumow.services;

import ru.naumow.dto.CourseDto;
import ru.naumow.models.Course;

public interface CoursesService {

    CourseDto getCourse(Long id);

}
