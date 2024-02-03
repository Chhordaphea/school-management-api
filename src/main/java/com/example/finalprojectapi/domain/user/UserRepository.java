package com.example.finalprojectapi.domain.user;

import com.example.finalprojectapi.domain.user.User;
import com.example.finalprojectapi.payload.dashboard.IDashboardResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 and u.status = '1'")
    Optional<User> findByUsername(String username);


    @Query(nativeQuery = true, value = """
            with t1 as (
                SELECT count(t.id) AS total_teacher
                   FROM tb_teachers t
                   WHERE t.created_by = ?1 and t.sts ='1'
            ),t2 as (
                 SELECT count(s.id) AS total_student
                   FROM tb_students s
                   WHERE s.created_by = ?1 and s.sts ='1'
            )
            SELECT * FROM t1,t2""")
    IDashboardResponse getDashboardData(Long userId);


}
