package com.example.finalprojectapi.controller;

import com.example.finalprojectapi.payload.profile.UpdateAccountRequest;
import com.example.finalprojectapi.service.profile.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bo/v1/profile")
@RequiredArgsConstructor
public class ProfileController extends RestControllerConfig{

    private final ProfileService  profileService;

    @GetMapping()
    public ResponseEntity<?> getAccountProfile() throws Throwable{
        return ok(profileService.getProfile());
    }

    @PatchMapping()
    public ResponseEntity<?> updateAccountProfile(@Valid @RequestBody UpdateAccountRequest payload) throws Throwable {
        profileService.updateProfile(payload);
        return ok();
    }
}
