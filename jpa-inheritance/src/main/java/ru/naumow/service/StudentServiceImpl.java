package ru.naumow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumow.model.Student;
import ru.naumow.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(String name, Integer mark) {
        studentRepository.save(Student.builder()
                .mark(mark)
                .name(name)
                .build());
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void update(Long id, String name, Integer mark) {
        Student student = Student.builder()
                .id(id)
                .name(name)
                .mark(mark)
                .build();
        studentRepository.update(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(id);
    }
}
