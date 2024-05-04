package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO update(UserDTO userDTO) {
        // Check if the user exists
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        if (existingUser == null) {
            // User does not exist, return null or throw an exception
            return null;
        }

        // Update user fields
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAge(userDTO.getAge());
        existingUser.setName(userDTO.getName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());

        // Save and return updated user
        return convertToDTO(userRepository.save(existingUser));
    }

    @Override
    public UserDTO delete(UserDTO userDTO) {
        // Check if the user exists
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        if (existingUser == null) {
            // User does not exist, return null or throw an exception
            return null;
        }

        // Delete the user
        userRepository.delete(existingUser);

        // Return deleted user
        return convertToDTO(existingUser);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            // User not found
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public List<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<UserDTO> findByName(String name) {
        return userRepository.findByName(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }
}

