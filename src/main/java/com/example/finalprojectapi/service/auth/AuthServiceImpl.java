package com.example.finalprojectapi.service.auth;


import com.example.finalprojectapi.common.api.StatusCode;
import com.example.finalprojectapi.component.security.JwtTokenUtil;
import com.example.finalprojectapi.component.security.UserAuthenticationProvider;
import com.example.finalprojectapi.domain.role.Role;
import com.example.finalprojectapi.domain.role.RoleRepository;
import com.example.finalprojectapi.domain.user.User;
import com.example.finalprojectapi.domain.user.UserRepository;
import com.example.finalprojectapi.enums.Status;
import com.example.finalprojectapi.enums.UserRoles;
import com.example.finalprojectapi.exception.BusinessException;
import com.example.finalprojectapi.payload.auth.AuthRequest;
import com.example.finalprojectapi.payload.auth.AuthResponse;
import com.example.finalprojectapi.payload.auth.SignupRequest;
import com.example.finalprojectapi.payload.security.SecurityUser;
import com.example.finalprojectapi.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Object login(AuthRequest payload) throws Throwable {
        Authentication authentication = userAuthenticationProvider.authenticate(payload.username(), payload.password());
        SecurityUser  securityUser = (SecurityUser) authentication.getPrincipal();
        String token = jwtTokenUtil.doGenerateToken(securityUser);
        return new AuthResponse(token,"Bearer",jwtTokenUtil.getExpireIn());
    }

    @Transactional
    @Override
    public void signup(SignupRequest payload) throws Throwable {

        String rawPassword;
        try {
            var decryptPassword = PasswordUtils.decrypt(payload.password());
           rawPassword = passwordEncoder.encode(decryptPassword);
        } catch (Exception e) {
            throw new BusinessException(StatusCode.PASSWORD_MUST_BE_ENCRYPTED);
        }
        //define role when create new user (default is role user)
        Role role = roleRepository.findByNameEqualsIgnoreCase(UserRoles.USER_ROLE.getValue()).orElseThrow(() -> new BusinessException(StatusCode.ROLE_NOT_FOUND));

        var user = User.builder()
                .fullname(payload.fullname())
                .username(payload.phonenumber())
                .password(rawPassword)
                .email(payload.email())
                .role(role)
                .status(Status.NORMAL.getValue())
                .build();
        userRepository.save(user);
    }
}
