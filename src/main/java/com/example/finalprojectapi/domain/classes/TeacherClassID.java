package com.example.finalprojectapi.domain.classes;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class TeacherClassID implements Serializable {

    @Column(name = "class_id" ,length = 100, nullable = false)
    private Long classId;

    @Column(name = "teacher_id" ,length = 100, nullable = false)
    private Long teacherId;

    @Builder
    public TeacherClassID(Long classId, Long teacherId, Long studentId) {
        this.classId = classId;
        this.teacherId = teacherId;
    }


}
