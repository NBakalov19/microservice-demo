package com.bakalov.school.web.client;

import com.bakalov.school.web.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.students-service-url}")
public interface StudentClient {

    @GetMapping("/school/{schoolId}")
    ResponseEntity<List<Student>> findAllStudentsBySchool(@PathVariable Integer schoolId);
}
