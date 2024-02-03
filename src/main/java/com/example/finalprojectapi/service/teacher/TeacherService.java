package com.example.finalprojectapi.service.teacher;

import com.example.finalprojectapi.payload.teacher.DeleteTeacherRequest;
import com.example.finalprojectapi.payload.teacher.TeacherRequest;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Object getTeachers(String searchValue,String startDate, String endDate, Pageable pageable);

    void createTeachers(TeacherRequest payload);

    void updateTeachers(TeacherRequest payload, Long id);
    void deleteTeachers(DeleteTeacherRequest payload);
}
