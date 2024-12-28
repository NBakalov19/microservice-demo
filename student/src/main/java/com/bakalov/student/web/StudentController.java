package com.bakalov.student.web;

import com.bakalov.student.data.entities.Student;
import com.bakalov.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Student>> findAllStudentsBySchool(@PathVariable Integer schoolId) {
        return ResponseEntity.ok(studentService.findAllBySchoolId(schoolId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }
}
