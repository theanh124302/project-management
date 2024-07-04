package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.EnvironmentDTO;
import com.example.projectmanagement.entity.Environment;
import com.example.projectmanagement.repository.EnvironmentRepository;
import com.example.projectmanagement.service.EnvironmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {
    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public List<EnvironmentDTO> findByProjectId(Long projectId) {
        return environmentRepository.findByProjectId(projectId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public EnvironmentDTO create(EnvironmentDTO environmentDTO) {
        return convertToDTO(environmentRepository.save(convertToEntity(environmentDTO)));
    }

    @Override
    public EnvironmentDTO update(EnvironmentDTO environmentDTO) {
        Environment environment = environmentRepository.findById(environmentDTO.getId()).orElse(null);
        if (environment != null) {
            environment.setName(environmentDTO.getName());
            environment.setProjectId(environmentDTO.getProjectId());
            environment.setDescription(environmentDTO.getDescription());
            environment.setUrl(environmentDTO.getUrl());
            return convertToDTO(environmentRepository.save(environment));
        }else {
            return null;
        }
    }

    @Transactional
    @Override
    public EnvironmentDTO delete(Long id) {
        EnvironmentDTO environmentDTO = findById(id);
        if (environmentDTO != null) {
            environmentRepository.delete(convertToEntity(environmentDTO));
        }
        return environmentDTO;
    }

    @Override
    public EnvironmentDTO findById(Long id) {
        Environment environment = environmentRepository.findById(id).orElse(null);
        return Objects.nonNull(environment) ? convertToDTO(environment) : null;
    }

    @Override
    public Long count() {
        return environmentRepository.count();
    }

    private EnvironmentDTO convertToDTO(Environment environment) {
        EnvironmentDTO environmentDTO = new EnvironmentDTO();
        environmentDTO.setId(environment.getId());
        environmentDTO.setName(environment.getName());
        environmentDTO.setProjectId(environment.getProjectId());
        environmentDTO.setDescription(environment.getDescription());
        environmentDTO.setUrl(environment.getUrl());
        return environmentDTO;
    }

    private Environment convertToEntity(EnvironmentDTO environmentDTO) {
        Environment environment = new Environment();
        environment.setId(environmentDTO.getId());
        environment.setName(environmentDTO.getName());
        environment.setProjectId(environmentDTO.getProjectId());
        environment.setDescription(environmentDTO.getDescription());
        environment.setUrl(environmentDTO.getUrl());
        return environment;
    }

}
