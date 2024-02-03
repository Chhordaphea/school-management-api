package com.example.finalprojectapi.domain.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class StudentClassID implements Serializable {

    @Column(name = "class_id" ,length = 100, nullable = false)
    private Long classId;

    @Column(name = "student_id" ,length = 100, nullable = false)
    private Long studentId;

    @Builder
    public StudentClassID(Long classId, Long studentId) {
        this.classId = classId;
        this.studentId = studentId;
    }


}
