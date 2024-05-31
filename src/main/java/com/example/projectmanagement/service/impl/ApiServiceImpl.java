package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ApiDTO;
import com.example.projectmanagement.entity.Api;
import com.example.projectmanagement.repository.ApiRepository;
import com.example.projectmanagement.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiRepository apiRepository;

    @Override
    public ApiDTO create(ApiDTO apiDTO) {
        Api api = convertToEntity(apiDTO);
        return convertToDTO(apiRepository.save(api));
    }

    @Override
    public ApiDTO update(ApiDTO apiDTO) {
        Optional<Api> existingApiOptional = apiRepository.findById(apiDTO.getId());
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            // Update fields from apiDTO to existingApi
            existingApi.setName(apiDTO.getName());
            existingApi.setDescription(apiDTO.getDescription());
            existingApi.setProjectId(apiDTO.getProjectId());
            existingApi.setFolderId(apiDTO.getFolderId());
            existingApi.setUrl(apiDTO.getUrl());
            existingApi.setMethod(apiDTO.getMethod());
            existingApi.setCreatedBy(apiDTO.getCreatedBy());
            existingApi.setCreatedAt(apiDTO.getCreatedAt());
            existingApi.setStatus(apiDTO.getStatus());
            existingApi.setExecutorID(apiDTO.getExecutorID());
            existingApi.setLifeCycle(apiDTO.getLifeCycle());
            return convertToDTO(apiRepository.save(existingApi));
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO delete(ApiDTO apiDTO) {
        Optional<Api> existingApiOptional = apiRepository.findById(apiDTO.getId());
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            apiRepository.delete(existingApi);
            return convertToDTO(existingApi);
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO findById(Long id) {
        Optional<Api> existingApiOptional = apiRepository.findById(id);
        return existingApiOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<ApiDTO> getAllApis(Pageable pageable) {
        return apiRepository.findAll(pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ApiDTO> findByName(String name, Pageable pageable) {
        return apiRepository.findByName(name, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ApiDTO> findByProjectId(Long projectId, Pageable pageable) {
        return apiRepository.findByProjectId(projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ApiDTO> findByFolderId(Long folderId, Pageable pageable) {
        return apiRepository.findByFolderId(folderId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ApiDTO> findByExecutorIDAndProjectId(Long executorID, Long projectId, Pageable pageable) {
        return apiRepository.findByExecutorIDAndProjectId(executorID, projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ApiDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable) {
        return apiRepository.findByProjectIdAndStatus(projectId, status, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ApiDTO> findByProjectIdAndCreatedBy(Long projectId, Long createdBy, Pageable pageable) {
        return apiRepository.findByProjectIdAndCreatedBy(projectId, createdBy, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return apiRepository.count();
    }

    private ApiDTO convertToDTO(Api api) {
        ApiDTO apiDTO = new ApiDTO();
        apiDTO.setId(api.getId());
        apiDTO.setProjectId(api.getProjectId());
        apiDTO.setFolderId(api.getFolderId());
        apiDTO.setName(api.getName());
        apiDTO.setDescription(api.getDescription());
        apiDTO.setUrl(api.getUrl());
        apiDTO.setMethod(api.getMethod());
        apiDTO.setCreatedBy(api.getCreatedBy());
        apiDTO.setCreatedAt(api.getCreatedAt());
        apiDTO.setStatus(api.getStatus());
        apiDTO.setExecutorID(api.getExecutorID());
        apiDTO.setLifeCycle(api.getLifeCycle());
        return apiDTO;
    }

    private Api convertToEntity(ApiDTO apiDTO) {
        Api api = new Api();
        api.setId(apiDTO.getId());
        api.setProjectId(apiDTO.getProjectId());
        api.setFolderId(apiDTO.getFolderId());
        api.setName(apiDTO.getName());
        api.setDescription(apiDTO.getDescription());
        api.setUrl(apiDTO.getUrl());
        api.setMethod(apiDTO.getMethod());
        api.setCreatedBy(apiDTO.getCreatedBy());
        api.setCreatedAt(apiDTO.getCreatedAt());
        api.setStatus(apiDTO.getStatus());
        api.setExecutorID(apiDTO.getExecutorID());
        api.setLifeCycle(apiDTO.getLifeCycle());
        return api;
    }
}