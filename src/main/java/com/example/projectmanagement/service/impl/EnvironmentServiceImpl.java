package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.EnvironmentDTO;
import com.example.projectmanagement.entity.Environment;
import com.example.projectmanagement.repository.EnvironmentRepository;
import com.example.projectmanagement.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public EnvironmentDTO create(EnvironmentDTO environmentDTO) {
        Environment environment = new Environment();
        environment.setName(environmentDTO.getName());
        environment.setDescription(environmentDTO.getDescription());
        environment.setStatus(environmentDTO.getStatus());
        environment.setProjectId(environmentDTO.getProjectId());
        environment.setCreatedBy(environmentDTO.getCreatedBy());
        environment.setValue(environmentDTO.getValue());
        environment.setCreatedAt(environmentDTO.getCreatedAt());
        return convertToDTO(environmentRepository.save(environment));
    }

    @Override
    public EnvironmentDTO update(EnvironmentDTO environmentDTO) {
        Optional<Environment> existingEnvironmentOptional = environmentRepository.findById(environmentDTO.getId());
        if (existingEnvironmentOptional.isPresent()) {
            Environment existingEnvironment = existingEnvironmentOptional.get();
            existingEnvironment.setName(environmentDTO.getName());
            existingEnvironment.setDescription(environmentDTO.getDescription());
            existingEnvironment.setStatus(environmentDTO.getStatus());
            existingEnvironment.setProjectId(environmentDTO.getProjectId());
            existingEnvironment.setCreatedBy(environmentDTO.getCreatedBy());
            existingEnvironment.setValue(environmentDTO.getValue());
            existingEnvironment.setCreatedAt(environmentDTO.getCreatedAt());
            // Update other fields if needed
            return convertToDTO(environmentRepository.save(existingEnvironment));
        } else {
            return null; // Handle the case where the environment doesn't exist
        }
    }

    @Override
    public EnvironmentDTO delete(Long id) {
        Optional<Environment> existingEnvironmentOptional = environmentRepository.findById(id);
        if (existingEnvironmentOptional.isPresent()) {
            Environment existingEnvironment = existingEnvironmentOptional.get();
            environmentRepository.delete(existingEnvironment);
            return convertToDTO(existingEnvironment);
        } else {
            return null; // Handle the case where the environment doesn't exist
        }
    }

    @Override
    public EnvironmentDTO findById(Long id) {
        Optional<Environment> environmentOptional = environmentRepository.findById(id);
        return environmentOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<EnvironmentDTO> findByProjectId(Long projectId, Pageable pageable) {
        Page<Environment> environmentPage = environmentRepository.findByProjectId(projectId, pageable);
        return environmentPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnvironmentDTO> findByProjectIdAndCreatedBy(Long projectId, Long createdBy, Pageable pageable) {
        Page<Environment> environmentPage = environmentRepository.findByProjectIdAndCreatedBy(projectId, createdBy, pageable);
        return environmentPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnvironmentDTO> findByName(String name, Pageable pageable) {
        Page<Environment> environmentPage = environmentRepository.findByName(name, pageable);
        return environmentPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnvironmentDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable) {
        Page<Environment> environmentPage = environmentRepository.findByProjectIdAndStatus(projectId, status, pageable);
        return environmentPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return environmentRepository.count();
    }

    private EnvironmentDTO convertToDTO(Environment environment) {
        EnvironmentDTO environmentDTO = new EnvironmentDTO();
        environmentDTO.setId(environment.getId());
        environmentDTO.setName(environment.getName());
        environmentDTO.setDescription(environment.getDescription());
        environmentDTO.setStatus(environment.getStatus());
        environmentDTO.setProjectId(environment.getProjectId());
        environmentDTO.setCreatedBy(environment.getCreatedBy());
        environmentDTO.setValue(environment.getValue());
        environmentDTO.setCreatedAt(environment.getCreatedAt());
        return environmentDTO;
    }
}
