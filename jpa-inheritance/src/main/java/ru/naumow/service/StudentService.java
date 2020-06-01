package ru.naumow.service;

import ru.naumow.model.Student;

public interface StudentService {

    void save(String name, Integer mark);

    Student getById(Long id);

    void update(Long id, String name, Integer mark);

    void delete(Long id);

}
