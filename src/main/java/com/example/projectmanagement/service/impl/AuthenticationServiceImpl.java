package com.example.projectmanagement.service.impl;


import com.example.projectmanagement.dto.*;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.model.auth.request.RefreshTokenRequestDTO;
import com.example.projectmanagement.model.auth.request.SignInRequestDTO;
import com.example.projectmanagement.model.auth.request.SignUpRequestDTO;
import com.example.projectmanagement.model.auth.response.AuthenticationResponseDTO;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.AuthenticationService;
import com.example.projectmanagement.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO signIn(SignInRequestDTO request) {
        System.out.println(request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        AuthUserDTO user = userRepository.findByUsername(request.getUsername()).map(AuthUserDTO::new).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
        authenticationResponseDTO.setToken(jwt);
        authenticationResponseDTO.setRefreshToken(refreshToken);
        return authenticationResponseDTO;
    }

    @Override
    public User signUp(SignUpRequestDTO request) {
        System.out.println(request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        //user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        return userRepository.save(user);
    }

    public AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO){
        String username = jwtService.extractUserName(refreshTokenRequestDTO.getToken());
        AuthUserDTO user = userRepository.findByUsername(username).map(AuthUserDTO::new).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequestDTO.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
            authenticationResponseDTO.setToken(jwt);
            authenticationResponseDTO.setRefreshToken(refreshTokenRequestDTO.getToken());
            return authenticationResponseDTO;
        }
        return null;
    }
}
