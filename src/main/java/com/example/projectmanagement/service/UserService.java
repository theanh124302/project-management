package com.example.projectmanagement.service;

import com.example.projectmanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void save(User user);
    Optional<User> update(User user);
    void delete(User user);
}
