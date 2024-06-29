package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProjectDTO;
import com.example.projectmanagement.enums.Role;
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
    List<ProjectDTO> findByUserId(Long userId, Pageable pageable);
    List<ProjectDTO> findByUsername(String username, Pageable pageable);
    ProjectDTO assignUser(Long projectId, Long userId, String role);
    ProjectDTO assignUserByUserUsername(Long projectId, String username, String role);
    ProjectDTO removeUser(Long projectId, Long userId, Long deleterId);
    ProjectDTO leaveProject(Long projectId, Long userId);
    ProjectDTO removeUserByUserUsername(Long projectId, String username, Long deleterId);
    Boolean checkValidUser(Long projectId, Long userId);
    Boolean checkEditable(Long projectId, Long apiId, Long userId, String lifeCycle);
    Long count();
}
