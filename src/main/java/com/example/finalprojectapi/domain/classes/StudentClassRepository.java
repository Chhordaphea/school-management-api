package com.example.finalprojectapi.domain.classes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentClassRepository extends JpaRepository<StudentClass, StudentClassID> {

    @Query("SELECT sc FROM StudentClass sc WHERE sc.classId = ?1")
    Page<StudentClass> findAllByClassId(Long classId, Pageable pageable);
}
