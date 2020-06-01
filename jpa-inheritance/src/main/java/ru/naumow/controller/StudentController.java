package ru.naumow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.naumow.model.Student;
import ru.naumow.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/api/students")
    public ResponseEntity<?> createStudent(
            @RequestParam("name") String name,
            @RequestParam("mark") Integer mark
    ) {
        studentService.save(name, mark);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/students/{studentId}/")
    public ResponseEntity<?> getStudent(@PathVariable("studentId") Long id) {
        Student student = studentService.getById(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/api/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/students/{studentId}")
    public ResponseEntity<?> updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam("name") String name,
            @RequestParam("mark") Integer mark
    ) {
        studentService.update(id, name, mark);
        return ResponseEntity.ok().build();
    }

}
