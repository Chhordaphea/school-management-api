package com.example.finalprojectapi.payload.profile;

import com.example.finalprojectapi.enums.Status;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfileResponse {

    private String name;

    private String gender;

    private String username;

    private String phoneNumber;

    private String email;

    private String profilePicture;

    private Status status;

    @Builder
    public ProfileResponse(String username,String name, String gender,String phoneNumber, String email, String profilePicture) {
        this.name = name;
        this.username = username;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profilePicture = profilePicture;
    }
}
