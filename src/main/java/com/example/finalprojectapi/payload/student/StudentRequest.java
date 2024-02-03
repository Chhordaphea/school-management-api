package com.example.finalprojectapi.payload.student;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    private String email;

    private String profileImage;

    @Builder

    public StudentRequest(String name, String gender, String address, String phoneNumber, String email, String profilePicture) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileImage = profilePicture;
    }
}
