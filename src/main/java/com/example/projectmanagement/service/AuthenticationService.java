package com.example.projectmanagement.service;

import com.example.projectmanagement.model.auth.response.AuthenticationResponseDTO;
import com.example.projectmanagement.model.auth.request.RefreshTokenRequestDTO;
import com.example.projectmanagement.model.auth.request.SignInRequestDTO;
import com.example.projectmanagement.model.auth.request.SignUpRequestDTO;
import com.example.projectmanagement.entity.User;

public interface AuthenticationService {
    AuthenticationResponseDTO signIn(SignInRequestDTO signinRequestDTO);

    User signUp(SignUpRequestDTO signUpRequestDTO);
    public AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}