package com.example.finalprojectapi.domain.classes;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_teacher_class")
@Entity
@IdClass(TeacherClassID.class)
public class TeacherClass {

    @Id
    @Column(name = "class_id" ,length = 100, nullable = false)
    private Long classId;

    @Id
    @Column(name = "teacher_id" ,length = 100, nullable = false)
    private Long teacherId;

    @Builder
    public TeacherClass(Long classId, Long teacherId) {
        this.classId = classId;
        this.teacherId = teacherId;
    }

}
