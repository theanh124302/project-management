package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.UserProjectDTO;

import java.util.List;

public interface UserProjectService {
    UserProjectDTO create(UserProjectDTO userProjectDTO);
    UserProjectDTO update(UserProjectDTO userProjectDTO);
    UserProjectDTO delete(UserProjectDTO userProjectDTO);
    UserProjectDTO findById(Long id);
    List<UserProjectDTO> findByProjectIdAndUserId(Long projectId, Long userId);
    Long count();
}
