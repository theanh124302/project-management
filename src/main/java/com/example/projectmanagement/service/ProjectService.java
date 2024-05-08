package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProjectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDTO create(ProjectDTO projectDTO);
    ProjectDTO update(ProjectDTO projectDTO);
    ProjectDTO delete(ProjectDTO projectDTO);
    ProjectDTO findById(Long id);
    List<ProjectDTO> getAllProjects(Pageable pageable);
    List<ProjectDTO> findByName(String name, Pageable pageable);
    List<ProjectDTO> findByLeaderId(Long leaderId, Pageable pageable);
    List<ProjectDTO> findByWorkspaceId(Long workspaceId, Pageable pageable);
    Long count();


    List<ProjectDTO> findUserId(Long userId, Pageable pageable);
}
