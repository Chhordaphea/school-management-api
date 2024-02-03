package com.example.finalprojectapi.domain.classes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherClassRepository extends JpaRepository<TeacherClass, TeacherClassID> {

    @Query("SELECT tc FROM TeacherClass tc WHERE tc.classId = ?1")
    Page<TeacherClass> findAllByClassId(Long classId, Pageable pageable);
}
