package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ProjectDTO;
import com.example.projectmanagement.entity.Project;
import com.example.projectmanagement.entity.UserProject;
import com.example.projectmanagement.enums.ProjectRole;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.UserProjectRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setLeaderId(projectDTO.getLeaderId());
        project.setCreationDate(projectDTO.getCreationDate());
        project.setStatus(projectDTO.getStatus());
        project.setStartDate(projectDTO.getStartDate());
        project.setExpectedEndDate(projectDTO.getExpectedEndDate());
        project.setNotes(projectDTO.getNotes());
        project.setVersion(projectDTO.getVersion());
        project.setPlatform(projectDTO.getPlatform());
        project.setTags(projectDTO.getTags());
        project.setCoverImage(projectDTO.getCoverImage());
        project.setSourceCode(projectDTO.getSourceCode());
        return convertToDTO(projectRepository.save(project));
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        Optional<Project> existingProjectOptional = projectRepository.findById(projectDTO.getId());
        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();
            existingProject.setName(projectDTO.getName());
            existingProject.setDescription(projectDTO.getDescription());
            existingProject.setLeaderId(projectDTO.getLeaderId());
            existingProject.setCreationDate(projectDTO.getCreationDate());
            existingProject.setStatus(projectDTO.getStatus());
            existingProject.setStartDate(projectDTO.getStartDate());
            existingProject.setExpectedEndDate(projectDTO.getExpectedEndDate());
            existingProject.setNotes(projectDTO.getNotes());
            existingProject.setVersion(projectDTO.getVersion());
            existingProject.setPlatform(projectDTO.getPlatform());
            existingProject.setTags(projectDTO.getTags());
            existingProject.setCoverImage(projectDTO.getCoverImage());
            existingProject.setSourceCode(projectDTO.getSourceCode());
            return convertToDTO(projectRepository.save(existingProject));
        } else {
            return null;
        }
    }

    @Override
    public ProjectDTO delete(ProjectDTO projectDTO) {
        Optional<Project> existingProjectOptional = projectRepository.findById(projectDTO.getId());
        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();
            projectRepository.delete(existingProject);
            return convertToDTO(existingProject);
        } else {
            return null;
        }
    }

    @Override
    public ProjectDTO findById(Long id) {
        Optional<Project> existingProjectOptional = projectRepository.findById(id);
        return existingProjectOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<ProjectDTO> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ProjectDTO> findByName(String name, Pageable pageable) {
        return projectRepository.findByName(name, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ProjectDTO> findByLeaderId(Long leaderId, Pageable pageable) {
        return projectRepository.findByLeaderId(leaderId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ProjectDTO> findByWorkspaceId(Long workspaceId, Pageable pageable) {
        return projectRepository.findByWorkspaceId(workspaceId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ProjectDTO> findByUserId(Long userId, Pageable pageable) {
        return projectRepository.findByUserId(userId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ProjectDTO> findByUsername(String username, Pageable pageable) {
        Long userId = userRepository.findByUsername(username).orElseThrow().getId();
        return projectRepository.findByUserId(userId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }


    @Override
    public ProjectDTO assignUser(Long projectId, Long userId, String role) {
        Optional<Project> existingProjectOptional = projectRepository.findById(projectId);
        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();
            UserProject userProject = new UserProject();
            userProject.setProjectId(projectId);
            userProject.setUserId(userId);
            userProject.setRole(ProjectRole.valueOf(role));
            userProjectRepository.save(userProject);
            return convertToDTO(existingProject);
        } else {
            return null;
        }
    }

    @Override
    public Long count() {
        return projectRepository.count();
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setLeaderId(project.getLeaderId());
        projectDTO.setCreationDate(project.getCreationDate());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setExpectedEndDate(project.getExpectedEndDate());
        projectDTO.setNotes(project.getNotes());
        projectDTO.setVersion(project.getVersion());
        projectDTO.setPlatform(project.getPlatform());
        projectDTO.setTags(project.getTags());
        projectDTO.setCoverImage(project.getCoverImage());
        projectDTO.setSourceCode(project.getSourceCode());
        return projectDTO;

    }
}
