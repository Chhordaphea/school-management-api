package com.example.finalprojectapi.service;

import com.example.finalprojectapi.payload.ClassRequest;
import org.springframework.data.domain.Pageable;

public interface ClassService {
    Object getClasses(String searchValue, Pageable pageable);
    void createClass(ClassRequest payload);

    Object getTeacherClasses(Long classId, Pageable pageable);

    Object getStudentClasses(Long classId,Pageable pageable);
}
