package com.example.finalprojectapi.domain.teacher;

import com.example.finalprojectapi.domain.user.User;
import com.example.finalprojectapi.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long>, JpaSpecificationExecutor<Teacher> {

    @Query("SELECT s FROM Teacher s WHERE s.id = ?1 AND s.users = ?2 AND s.status = ?3")
    Optional<Teacher> findByIdAndStatusAndUser(Long aLong, User user, Status status);

}
