package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ProjectDTO;
import com.example.projectmanagement.entity.Project;
import com.example.projectmanagement.entity.UserProject;
import com.example.projectmanagement.enums.ProjectRole;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.UserProjectRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.ProjectService;
import jakarta.transaction.Transactional;
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
        project.setStatus(projectDTO.getStatus());
        project.setStartDate(projectDTO.getStartDate());
        project.setExpectedEndDate(projectDTO.getExpectedEndDate());
        project.setVersion(projectDTO.getVersion());
        project.setCoverImage(projectDTO.getCoverImage());
        project.setNumberOfMembers(0L);
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
            existingProject.setStatus(projectDTO.getStatus());
            existingProject.setStartDate(projectDTO.getStartDate());
            existingProject.setExpectedEndDate(projectDTO.getExpectedEndDate());
            existingProject.setVersion(projectDTO.getVersion());
            existingProject.setCoverImage(projectDTO.getCoverImage());
            existingProject.setNumberOfMembers(projectDTO.getNumberOfMembers());
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
            UserProject userProject = userProjectRepository.findByUserIdAndProjectId(userId, projectId).orElse(null);
            if (userProject != null) {
                userProject.setProjectId(projectId);
                userProject.setUserId(userId);
                userProject.setRole(ProjectRole.valueOf(role));
                userProjectRepository.save(userProject);
                return convertToDTO(existingProject);
            }else {
                userProject = new UserProject();
                userProject.setProjectId(projectId);
                userProject.setUserId(userId);
                userProject.setRole(ProjectRole.valueOf(role));
                userProjectRepository.save(userProject);
                existingProject.setNumberOfMembers(existingProject.getNumberOfMembers() + 1);
                projectRepository.save(existingProject);
                return convertToDTO(existingProject);
            }
        } else {
            return null;
        }
    }

    @Override
    public ProjectDTO assignUserByUserUsername(Long projectId, String username, String role) {
        Long userId = userRepository.findByUsername(username).orElseThrow().getId();
        return assignUser(projectId, userId, role);
    }

    @Override
    public ProjectDTO removeUser(Long projectId, Long userId, Long deleterId) {
        Optional<Project> existingProjectOptional = projectRepository.findById(projectId);
        if (existingProjectOptional.isPresent()) {
            if (existingProjectOptional.get().getLeaderId().equals(deleterId)&&!existingProjectOptional.get().getLeaderId().equals(userId)) {
                Project existingProject = existingProjectOptional.get();
                userProjectRepository.deleteById(userProjectRepository.findByUserIdAndProjectId(userId, projectId).orElseThrow().getId());
                existingProject.setNumberOfMembers(existingProject.getNumberOfMembers() - 1);
                projectRepository.save(existingProject);
                return convertToDTO(existingProject);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public ProjectDTO leaveProject(Long projectId, Long userId) {
        Optional<Project> existingProjectOptional = projectRepository.findById(projectId);
        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();
            userProjectRepository.deleteById(userProjectRepository.findByUserIdAndProjectId(userId, projectId).orElseThrow().getId());
            existingProject.setNumberOfMembers(existingProject.getNumberOfMembers() - 1);
            projectRepository.save(existingProject);
            return convertToDTO(existingProject);
        }
        return null;
    }

    @Override
    public ProjectDTO removeUserByUserUsername(Long projectId, String username, Long deleterId) {
        Long userId = userRepository.findByUsername(username).orElseThrow().getId();
        return removeUser(projectId, userId, deleterId);
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
        projectDTO.setStatus(project.getStatus());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setExpectedEndDate(project.getExpectedEndDate());
        projectDTO.setVersion(project.getVersion());
        projectDTO.setCoverImage(project.getCoverImage());
        projectDTO.setNumberOfMembers(project.getNumberOfMembers());
        return projectDTO;

    }
}
