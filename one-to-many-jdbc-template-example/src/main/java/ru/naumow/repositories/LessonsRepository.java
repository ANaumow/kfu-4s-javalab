package ru.naumow.repositories;


import ru.naumow.models.Lesson;

import java.util.List;

public interface LessonsRepository extends CrudRepository<Long, Lesson> {

    List<Lesson> findByCourseId(Long courseId);
    List<Lesson> findAll();

}
