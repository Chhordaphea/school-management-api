package com.example.finalprojectapi.payload.auth;

public record AuthRequest(
        String username,
        String password
) {
}
