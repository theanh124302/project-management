package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ProjectFileDTO;
import com.example.projectmanagement.entity.ProjectFile;
import com.example.projectmanagement.repository.ProjectFileRepository;
import com.example.projectmanagement.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectFileServiceImpl implements ProjectFileService {

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Autowired
    private ProjectFileRepository projectFileRepository;

    @Override
    public ProjectFileDTO create(ProjectFileDTO projectFileDTO, MultipartFile file) {
        projectFileDTO.setName(file.getOriginalFilename());
        projectFileDTO.setUuid(java.util.UUID.randomUUID().toString());
        projectFileDTO.setType(file.getContentType());
        projectFileDTO.setLocalPath(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\file\\" + projectFileDTO.getUuid() + projectFileDTO.getName());
        projectFileDTO.setUrl("http://localhost:8080/file/" + projectFileDTO.getUuid() + projectFileDTO.getName());
        saveFile(file, projectFileDTO.getLocalPath());
        ProjectFile projectFile = convertToEntity(projectFileDTO);
        return convertToDTO(projectFileRepository.save(projectFile));
    }

    @Override
    public ProjectFileDTO delete(Long id) {
        Optional<ProjectFile> existingProjectFileOptional = projectFileRepository.findById(id);
        if (existingProjectFileOptional.isPresent()) {
            ProjectFile existingProjectFile = existingProjectFileOptional.get();
            projectFileRepository.delete(existingProjectFile);
            return convertToDTO(existingProjectFile);
        } else {
            return null;
        }
    }

    @Override
    public ProjectFileDTO findById(Long id) {
        Optional<ProjectFile> existingProjectFileOptional = projectFileRepository.findById(id);
        return existingProjectFileOptional.map(this::convertToDTO).orElse(null);
    }


    @Override
    public List<ProjectFileDTO> findByProjectId(Long projectId, Pageable pageable) {
        return projectFileRepository.findByProjectId(projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long countByProjectId(Long projectId) {
        return projectFileRepository.countByProjectId(projectId);
    }


    @Override
    public Long count() {
        return projectFileRepository.count();
    }

    private ProjectFileDTO convertToDTO(ProjectFile projectFile) {
        ProjectFileDTO projectFileDTO = new ProjectFileDTO();
        projectFileDTO.setId(projectFile.getId());
        projectFileDTO.setName(projectFile.getName());
        projectFileDTO.setType(projectFile.getType());
        projectFileDTO.setDescription(projectFile.getDescription());
        projectFileDTO.setUuid(projectFile.getUuid());
        projectFileDTO.setUrl(projectFile.getUrl());
        projectFileDTO.setProjectId(projectFile.getProjectId());
        return projectFileDTO;
    }

    private ProjectFile convertToEntity(ProjectFileDTO projectFileDTO) {
        ProjectFile projectFile = new ProjectFile();
        projectFile.setId(projectFileDTO.getId());
        projectFile.setName(projectFileDTO.getName());
        projectFile.setType(projectFileDTO.getType());
        projectFile.setDescription(projectFileDTO.getDescription());
        projectFile.setUuid(projectFileDTO.getUuid());
        projectFile.setUrl(projectFileDTO.getUrl());
        projectFile.setProjectId(projectFileDTO.getProjectId());
        return projectFile;
    }

    private void saveFile(MultipartFile file, String directory) {
        try {
            file.transferTo(new File(directory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}