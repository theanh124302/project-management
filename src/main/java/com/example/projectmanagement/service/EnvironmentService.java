package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.EnvironmentDTO;

import java.util.List;

public interface EnvironmentService {
    EnvironmentDTO create(EnvironmentDTO environmentDTO);
    EnvironmentDTO update(EnvironmentDTO environmentDTO);
    EnvironmentDTO delete(Long id);
    EnvironmentDTO findById(Long id);
    List<EnvironmentDTO> findByProjectId(Long projectId);
    Long count();
}
