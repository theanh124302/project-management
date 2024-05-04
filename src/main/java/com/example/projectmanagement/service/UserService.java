package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO update(UserDTO userDTO);
    UserDTO delete(UserDTO userDTO);
    List<UserDTO> getAllUsers(Pageable pageable);
    List<UserDTO> findByName(String name, Pageable pageable);
    UserDTO findByUsername(String username);
    UserDTO findByEmail(String email);
    UserDTO findByPhoneNumber(String phoneNumber);
    UserDTO findByEmailOrUsername(String email, String username);
    Long count();
}
