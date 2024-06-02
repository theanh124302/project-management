package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.EnvironmentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnvironmentService {
    EnvironmentDTO create(EnvironmentDTO environmentDTO);
    EnvironmentDTO update(EnvironmentDTO environmentDTO);
    EnvironmentDTO delete(Long id);
    EnvironmentDTO findById(Long id);
    List<EnvironmentDTO> findByProjectId(Long projectId, Pageable pageable);
    List<EnvironmentDTO> findByName(String name, Pageable pageable);
    List<EnvironmentDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable);
    Long count();
}
