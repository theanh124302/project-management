package com.example.projectmanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService {
    UserDetailsService userDetailsService();
}