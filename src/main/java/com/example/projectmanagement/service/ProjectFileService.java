package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DocsDTO;
import com.example.projectmanagement.dto.ProjectFileDTO;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectFileService {
    ProjectFileDTO create(ProjectFileDTO projectFileDTO, MultipartFile file);
    ProjectFileDTO update(ProjectFileDTO projectFileDTO);
    ProjectFileDTO delete(Long id);
    ProjectFileDTO findById(Long id);
    Resource findResourceById(Long id);
    List<ProjectFileDTO> findByProjectId(Long projectId, Pageable pageable);
    List<Resource> findResourcesByProjectId(Long projectId, Pageable pageable);
    Long count();
}