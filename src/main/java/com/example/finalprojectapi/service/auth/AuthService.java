package com.example.finalprojectapi.service.auth;


import com.example.finalprojectapi.payload.auth.AuthRequest;
import com.example.finalprojectapi.payload.auth.SignupRequest;

public interface AuthService{
    Object login(AuthRequest payload) throws Throwable;

    void signup(SignupRequest payload) throws Throwable;
}
