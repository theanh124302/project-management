package com.example.projectmanagement.service;

import com.example.projectmanagement.dao.AuthenticationResponse;
import com.example.projectmanagement.dao.RefreshTokenRequest;
import com.example.projectmanagement.dao.SignInRequest;
import com.example.projectmanagement.dao.SignUpRequest;
import com.example.projectmanagement.dto.User;

public interface AuthenticationService {
    AuthenticationResponse signIn(SignInRequest signinRequest);

    User signUp(SignUpRequest signUpRequest);
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}