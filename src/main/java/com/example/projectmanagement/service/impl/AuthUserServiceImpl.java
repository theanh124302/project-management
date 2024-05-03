package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.AuthUserDTO;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username).map(AuthUserDTO::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
