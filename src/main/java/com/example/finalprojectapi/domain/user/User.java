package com.example.finalprojectapi.domain.user;

import com.example.finalprojectapi.domain.role.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Types;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "tb_users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String fullname;

    private String gender;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100)
    private String phoneNumber;

    private String profileImage;

    @Column(length = Types.CHAR, nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Builder
    public User(Long id, String username, String fullname, String gender, String password, String email, String phoneNumber,String status, String profileImage, Role role) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.role = role;
        this.status = status;
    }
}
