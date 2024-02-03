package com.example.finalprojectapi.service.profile;

import com.example.finalprojectapi.payload.profile.UpdateAccountRequest;

public interface ProfileService {
    Object getProfile() throws Throwable;

    void updateProfile(UpdateAccountRequest payload) throws Throwable;

}