package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProjectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDTO create(ProjectDTO projectDTO);
    ProjectDTO update(ProjectDTO projectDTO);
    ProjectDTO delete(ProjectDTO projectDTO);
    List<ProjectDTO> getAllProjects(Pageable pageable);
    List<ProjectDTO> findByName(String name, Pageable pageable);
    List<ProjectDTO> findByLeaderId(Long leaderId, Pageable pageable);
    Long count();
}
