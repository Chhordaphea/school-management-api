package com.example.finalprojectapi.domain.student;

import com.example.finalprojectapi.domain.user.User;
import com.example.finalprojectapi.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {

    @Query("SELECT s FROM Student s WHERE s.id = ?1 AND s.users = ?2 AND s.status = ?3")
    Optional<Student> findByIdAndStatusAndUser(Long aLong, User user, Status status);
}
