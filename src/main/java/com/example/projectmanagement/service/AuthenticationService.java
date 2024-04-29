package com.example.projectmanagement.service;

import com.example.projectmanagement.dao.AuthenticationResponseDTO;
import com.example.projectmanagement.dao.RefreshTokenRequestDTO;
import com.example.projectmanagement.dao.SignInRequestDTO;
import com.example.projectmanagement.dao.SignUpRequestDTO;
import com.example.projectmanagement.dto.User;

public interface AuthenticationService {
    AuthenticationResponseDTO signIn(SignInRequestDTO signinRequestDTO);

    User signUp(SignUpRequestDTO signUpRequestDTO);
    public AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}