package com.example.finalprojectapi.domain.student;

import com.example.finalprojectapi.domain.CreatableEntity;
import com.example.finalprojectapi.domain.classes.Class;
import com.example.finalprojectapi.domain.user.User;
import com.example.finalprojectapi.enums.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Getter
@Setter
@Entity
@Table(name = "tb_students")
@NoArgsConstructor
public class Student extends CreatableEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name="profile_picture")
    private String profilePicture;

    @Column(name = "sts", nullable = false, length = Types.CHAR)
    @JdbcTypeCode(Types.CHAR)
    @Convert(converter = Status.Converter.class)
    private Status status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User users;

    @Builder
    public Student(Long id, String name, String gender, String address, String phoneNumber, String email, String profilePicture, Status status, User users) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profilePicture = profilePicture;
        this.status = status;
        this.users = users;
    }
}
