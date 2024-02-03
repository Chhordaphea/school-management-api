package com.example.finalprojectapi.domain.classes;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_student_class")
@Entity
@IdClass(StudentClassID.class)
public class StudentClass {

    @Id
    @Column(name = "class_id" ,length = 100, nullable = false)
    private Long classId;

    @Id
    @Column(name = "student_id" ,length = 100, nullable = false)
    private Long studentId;

    @Builder
    public StudentClass(Long classId, Long studentId) {
        this.classId = classId;
        this.studentId = studentId;
    }

}
