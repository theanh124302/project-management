package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DocsDTO;
import com.example.projectmanagement.dto.ProjectFileDTO;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectFileService {
    ProjectFileDTO create(ProjectFileDTO projectFileDTO, MultipartFile file);
    ProjectFileDTO delete(Long id);
    ProjectFileDTO findById(Long id);
    List<ProjectFileDTO> findByProjectId(Long projectId, Pageable pageable);
    Long countByProjectId(Long projectId);
    Long count();
}