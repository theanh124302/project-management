package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.UserProjectDTO;
import com.example.projectmanagement.entity.UserProject;
import com.example.projectmanagement.repository.UserProjectRepository;
import com.example.projectmanagement.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProjectServiceImpl implements UserProjectService {

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Override
    public UserProjectDTO create(UserProjectDTO userProjectDTO) {
        return null;
    }

    @Override
    public UserProjectDTO update(UserProjectDTO userProjectDTO) {
        return null;
    }

    @Override
    public UserProjectDTO delete(UserProjectDTO userProjectDTO) {
        return null;
    }

    @Override
    public UserProjectDTO findById(Long id) {
        return null;
    }

    @Override
    public List<UserProjectDTO> findByProjectIdAndUserId(Long projectId, Long userId) {
        return userProjectRepository.findByProjectIdAndUserId(projectId, userId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return 0L;
    }

    private UserProjectDTO convertToDTO(UserProject userProject) {
        return UserProjectDTO.builder()
                .id(userProject.getId())
                .projectId(userProject.getProjectId())
                .userId(userProject.getUserId())
                .role(userProject.getRole())
                .build();
    }
}
