package com.example.finalprojectapi.payload.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SignupRequest(

        @NotBlank
        @Length(max = 100)
        String fullname,

        @NotBlank
        @Length(max = 100)
        String phonenumber,

        @Length(max = 100)
        String email,

        @NotBlank
        @Length(max = 100)
        String password
) {
}
