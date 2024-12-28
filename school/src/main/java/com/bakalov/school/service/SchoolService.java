package com.bakalov.school.service;

import com.bakalov.school.data.entities.School;
import com.bakalov.school.data.repositories.SchoolRepository;
import com.bakalov.school.web.FullSchoolResponse;
import com.bakalov.school.web.Student;
import com.bakalov.school.web.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;

    //    todo add dto record
    public void saveSchool(School school) {
        schoolRepository.save(school);
    }

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findAllSchoolsWithStudents(Integer schoolId) {
        School school =
            schoolRepository.findById(schoolId)
                            .orElseThrow(() -> new IllegalArgumentException("School not found"));

        List<Student> students = studentClient.findAllStudentsBySchool(schoolId).getBody();

        return FullSchoolResponse.builder()
                                 .name(school.getName())
                                 .email(school.getEmail())
                                 .students(students)
                                 .build();
    }
}
