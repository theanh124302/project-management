package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DefineRequestDTO;
import com.example.projectmanagement.entity.Comment;
import com.example.projectmanagement.entity.DefineRequest;
import com.example.projectmanagement.repository.DefineRequestRepository;
import com.example.projectmanagement.service.DefineRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class DefineRequestServiceImpl implements DefineRequestService {

    @Autowired
    private DefineRequestRepository defineRequestRepository;

    @Override
    public DefineRequestDTO create(DefineRequestDTO defineRequestDTO) {
        DefineRequest defineRequest = convertToEntity(defineRequestDTO);
        defineRequest.setCreatedAt(Timestamp.from(Instant.now()));
        return convertToDTO(defineRequestRepository.save(defineRequest));
    }

    @Override
    public DefineRequestDTO update(DefineRequestDTO defineRequestDTO) {
        Optional<DefineRequest> existingDefineRequestOptional = defineRequestRepository.findById(defineRequestDTO.getId());
        if (existingDefineRequestOptional.isPresent()) {
            DefineRequest existingDefineRequest = existingDefineRequestOptional.get();
            existingDefineRequest.setProjectId(defineRequestDTO.getProjectId());
            existingDefineRequest.setUrl(defineRequestDTO.getUrl());
            existingDefineRequest.setUserId(defineRequestDTO.getUserId());
            existingDefineRequest.setTaskId(defineRequestDTO.getTaskId());
            existingDefineRequest.setDescription(defineRequestDTO.getDescription());
            existingDefineRequest.setContent(defineRequestDTO.getContent());
            return convertToDTO(defineRequestRepository.save(existingDefineRequest));
        } else {
            return null; // Handle the case where the define request doesn't exist
        }
    }

    @Override
    public DefineRequestDTO delete(Long id) {
        Optional<DefineRequest> existingDefineRequestOptional = defineRequestRepository.findById(id);
        if (existingDefineRequestOptional.isPresent()) {
            DefineRequest existingDefineRequest = existingDefineRequestOptional.get();
            defineRequestRepository.delete(existingDefineRequest);
            return convertToDTO(existingDefineRequest);
        } else {
            return null; // Handle the case where the define request doesn't exist
        }
    }

    @Override
    public DefineRequestDTO findById(Long id) {
        Optional<DefineRequest> defineRequestOptional = defineRequestRepository.findById(id);
        return defineRequestOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DefineRequestDTO> findByProjectId(Long projectId, Pageable pageable) {
        Page<DefineRequest> defineRequestPage = defineRequestRepository.findByProjectId(projectId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<DefineRequestDTO> findByUserId(Long userId, Pageable pageable) {
        Page<DefineRequest> defineRequestPage = defineRequestRepository.findByUserId(userId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<DefineRequestDTO> findByTaskId(Long taskId, Pageable pageable) {
        Page<DefineRequest> defineRequestPage = defineRequestRepository.findByTaskId(taskId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return defineRequestRepository.count();
    }

    private DefineRequestDTO convertToDTO(DefineRequest defineRequest) {
        DefineRequestDTO defineRequestDTO = new DefineRequestDTO();
        defineRequestDTO.setId(defineRequest.getId());
        defineRequestDTO.setUrl(defineRequest.getUrl());
        defineRequestDTO.setProjectId(defineRequest.getProjectId());
        defineRequestDTO.setUserId(defineRequest.getUserId());
        defineRequestDTO.setTaskId(defineRequest.getTaskId());
        defineRequestDTO.setDescription(defineRequest.getDescription());
        defineRequestDTO.setContent(defineRequest.getContent());
        return defineRequestDTO;
    }

    private DefineRequest convertToEntity(DefineRequestDTO defineRequestDTO) {
        DefineRequest defineRequest = new DefineRequest();
        defineRequest.setId(defineRequestDTO.getId());
        defineRequest.setUrl(defineRequestDTO.getUrl());
        defineRequest.setProjectId(defineRequestDTO.getProjectId());
        defineRequest.setUserId(defineRequestDTO.getUserId());
        defineRequest.setTaskId(defineRequestDTO.getTaskId());
        defineRequest.setDescription(defineRequestDTO.getDescription());
        defineRequest.setContent(defineRequestDTO.getContent());
        return defineRequest;
    }
}
