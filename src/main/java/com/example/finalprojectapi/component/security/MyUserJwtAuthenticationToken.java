package com.example.finalprojectapi.component.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class MyUserJwtAuthenticationToken<T extends UserDetails> extends JwtAuthenticationToken {

    private final T securityUser;

    public MyUserJwtAuthenticationToken(Jwt jwt, T securityUser) {
        super(jwt, securityUser.getAuthorities());
        this.securityUser = securityUser;
    }

    @Override
    public Object getPrincipal() {
        return this.securityUser;
    }

}
