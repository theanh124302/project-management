package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.UserProjectDTO;

public interface UserProjectService {
    UserProjectDTO create(UserProjectDTO userProjectDTO);
    UserProjectDTO update(UserProjectDTO userProjectDTO);
    UserProjectDTO delete(UserProjectDTO userProjectDTO);
    UserProjectDTO findById(Long id);
    Long count();
}
