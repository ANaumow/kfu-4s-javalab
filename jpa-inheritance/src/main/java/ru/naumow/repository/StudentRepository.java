package ru.naumow.repository;

import ru.naumow.model.Student;

public interface StudentRepository {

    void save(Student entity);

    Student findById(Long id);

    void update(Student entity);

    void delete(Long id);
}
