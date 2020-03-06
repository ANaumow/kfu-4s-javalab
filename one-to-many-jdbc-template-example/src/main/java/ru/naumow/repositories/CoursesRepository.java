package ru.naumow.repositories;


import ru.naumow.models.Course;

import java.util.List;

public interface CoursesRepository extends CrudRepository<Long, Course> {

    List<Course> findAll();
}
