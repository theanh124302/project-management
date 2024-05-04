package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.template.ResponseTemplate;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO update(UserDTO userDTO);
    UserDTO delete(UserDTO userDTO);
    List<UserDTO> findAll();
    UserDTO findByUsername(String username);
    List<UserDTO> findByEmail(String email);
    List<UserDTO> findByName(String name);
}
