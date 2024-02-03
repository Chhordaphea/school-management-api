package com.example.finalprojectapi.payload.teacher;

import com.example.finalprojectapi.enums.Status;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TeacherList {

    private Long id;

    private String name;

    private String gender;

    private String address;

    private String phoneNumber;

    private String email;

    private String profileImage;

    private Status status;

    private ZonedDateTime createdAt;

    @Builder
    public TeacherList(Long id, String name, String gender, String address, String phoneNumber, String email, String profileImage, Status status, ZonedDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileImage = profileImage;
        this.status = status;
        this.createdAt = createdAt;
    }
}
