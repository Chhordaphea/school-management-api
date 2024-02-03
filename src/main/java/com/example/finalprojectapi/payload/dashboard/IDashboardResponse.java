package com.example.finalprojectapi.payload.dashboard;

import org.springframework.beans.factory.annotation.Value;

public interface IDashboardResponse {

    @Value("#{target.total_teacher}")
    Long getTotalTeacher();

    @Value("#{target.total_student}")
    Long getTotalStudent();
}
