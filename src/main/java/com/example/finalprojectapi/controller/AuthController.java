package com.example.finalprojectapi.controller;

import com.example.finalprojectapi.payload.auth.AuthRequest;
import com.example.finalprojectapi.payload.auth.SignupRequest;
import com.example.finalprojectapi.service.auth.AuthService;
import com.example.finalprojectapi.utils.PasswordUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bo/v1/auth")
@RequiredArgsConstructor
public class AuthController extends RestControllerConfig{

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Object login(@RequestBody @Valid AuthRequest payload) throws Throwable{
        System.err.println(passwordEncoder.encode("admin"));
        return ok(authService.login(payload));
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequest payload) throws Throwable{
        authService.signup(payload);
        return ok();
    }

}
