package com.example.finalprojectapi.payload.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;


public record UpdateAccountRequest(
        @NotBlank
        String fullname,

        @NotBlank
        String email,

        @NotBlank
        String gender,

        @NotBlank
        String phonenumber,

        @JsonProperty("profile_image")
        String profileImage
) {
}
