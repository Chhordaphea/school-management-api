package com.example.finalprojectapi.service.student;

import com.example.finalprojectapi.payload.student.DeleteStudentRequest;
import com.example.finalprojectapi.payload.student.StudentRequest;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Object getStudents(String searchValue,String startDate, String endDate, Pageable pageable);

    void createStudent(StudentRequest payload);

    void updateStudent(StudentRequest payload, Long id);
    void deleteStudent(DeleteStudentRequest payload);
}
