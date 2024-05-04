package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO update(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAge(userDTO.getAge());
        existingUser.setName(userDTO.getName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        return convertToDTO(userRepository.save(existingUser));
    }

    @Override
    public UserDTO delete(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        userRepository.delete(existingUser);
        return convertToDTO(existingUser);
    }

    @Override
    public List<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent().stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<UserDTO> findByName(String name, Pageable pageable) {
        return userRepository.findByName(name, pageable).getContent().stream().map(this::convertToDTO).toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO findByPhoneNumber(String phoneNumber) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO findByEmailOrUsername(String email, String username) {
        Optional<User> userOptional = userRepository.findByEmailOrUsername(email, username);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public Long count() {
        return userRepository.count();
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

