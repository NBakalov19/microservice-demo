package com.bakalov.school.web;

import com.bakalov.school.data.entities.School;
import com.bakalov.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<School>> findAllStudents() {
        return ResponseEntity.ok(schoolService.findAll());
    }

    @GetMapping("/with-students/{schoolId}")
    public ResponseEntity<?> findAllStudentsWithStudents(@PathVariable Integer schoolId) {
        try {
            return ResponseEntity.ok(schoolService.findAllSchoolsWithStudents(schoolId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(BAD_REQUEST)
                                 .body(e.getMessage());
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void saveSchool(@RequestBody School school) {
        schoolService.saveSchool(school);
    }
}
