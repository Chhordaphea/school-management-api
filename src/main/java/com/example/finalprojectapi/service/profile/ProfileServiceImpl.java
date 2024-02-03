package com.example.finalprojectapi.service.profile;


import com.example.finalprojectapi.domain.user.User;
import com.example.finalprojectapi.domain.user.UserRepository;
import com.example.finalprojectapi.helper.AuthHelper;
import com.example.finalprojectapi.payload.profile.ProfileResponse;
import com.example.finalprojectapi.payload.profile.UpdateAccountRequest;
import com.example.finalprojectapi.properties.FileInfoConfig;
import com.example.finalprojectapi.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    private final FileInfoConfig fileInfoConfig;

    @Override
    public Object getProfile() throws Throwable {

        var profile = userRepository.findById(AuthHelper.getUserId()).orElse(new User());

        return ProfileResponse.builder()
                .name(profile.getFullname())
                .username(profile.getUsername())
                .gender(profile.getGender())
                .phoneNumber(profile.getPhoneNumber())
                .email(profile.getEmail())
                .profilePicture(ImageUtil.getImageUrl(fileInfoConfig.getBaseUrl(),profile.getProfileImage()))
                .build();
    }

    @Override
    public void updateProfile(UpdateAccountRequest payload) throws Throwable {
        var profile = userRepository.findById(AuthHelper.getUserId()).orElse(new User());
        profile.setFullname(payload.fullname());
        profile.setGender(payload.gender());
        profile.setEmail(payload.email());
        profile.setPhoneNumber(payload.phonenumber());
        if (StringUtils.isNotBlank(payload.profileImage())) {
            profile.setProfileImage(payload.profileImage());
        }
        userRepository.save(profile);
    }


}