package ru.naumow;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.naumow.config.ApplicationConfiguration;
import ru.naumow.dto.CourseDto;
import ru.naumow.models.Course;
import ru.naumow.repositories.CoursesRepository;
import ru.naumow.repositories.LessonsRepository;
import ru.naumow.repositories.spring.CoursesRepositoryJdbcTemplateImpl;
import ru.naumow.repositories.spring.LessonRepositoryJdbcTemplateImpl;
import ru.naumow.services.CoursesService;
import ru.naumow.services.CoursesServiceImpl;

import javax.sql.DataSource;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        CoursesService coursesService = context.getBean("courseService", CoursesService.class);
        CourseDto courseDto = coursesService.getCourse(1L);
        System.out.println(courseDto);
    }

}
