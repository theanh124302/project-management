package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ApiDTO;
import com.example.projectmanagement.entity.Api;
import com.example.projectmanagement.enums.Method;
import com.example.projectmanagement.repository.ApiRepository;
import com.example.projectmanagement.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
            existingApi.setName(apiDTO.getName());
            existingApi.setDescription(apiDTO.getDescription());
            existingApi.setProjectId(apiDTO.getProjectId());
            existingApi.setFolderId(apiDTO.getFolderId());
            existingApi.setUrl(apiDTO.getUrl());
            existingApi.setMethod(apiDTO.getMethod());
            existingApi.setEnvironmentId(apiDTO.getEnvironmentId());
            existingApi.setCreatedBy(apiDTO.getCreatedBy());
            existingApi.setStatus(apiDTO.getStatus());
            existingApi.setParameters(apiDTO.getParameters());
            existingApi.setBodyJson(apiDTO.getBodyJson());
            existingApi.setToken(apiDTO.getToken());
            existingApi.setActivityDiagram(apiDTO.getActivityDiagram());
            existingApi.setBusinessProcess(apiDTO.getBusinessProcess());
            existingApi.setClassDiagram(apiDTO.getClassDiagram());
            existingApi.setSequenceDiagram(apiDTO.getSequenceDiagram());
            existingApi.setTechnicalRequirements(apiDTO.getTechnicalRequirements());
            existingApi.setUseCaseDiagram(apiDTO.getUseCaseDiagram());
            existingApi.setUserRequirements(apiDTO.getUserRequirements());
            existingApi.setInstallationGuide(apiDTO.getInstallationGuide());
            existingApi.setLifeCycle(apiDTO.getLifeCycle());
            return convertToDTO(apiRepository.save(existingApi));
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO updateUrlAndMethod(Long id, String url, String method) {
        Optional<Api> existingApiOptional = apiRepository.findById(id);
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            existingApi.setUrl(url);
            existingApi.setMethod(Method.valueOf(method));
            return convertToDTO(apiRepository.save(existingApi));
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO updateParametersAndBodyAndTokenAndHeader(Long id, String parameters, String body, String token, String header) {
        Optional<Api> existingApiOptional = apiRepository.findById(id);
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            existingApi.setBodyJson(body);
            existingApi.setToken(token);
            existingApi.setParameters(parameters);
            return convertToDTO(apiRepository.save(existingApi));
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagram(Long id, String useCaseDiagram, String sequenceDiagram, String activityDiagram, String classDiagram) {
        Optional<Api> existingApiOptional = apiRepository.findById(id);
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            existingApi.setUseCaseDiagram(useCaseDiagram);
            existingApi.setSequenceDiagram(sequenceDiagram);
            existingApi.setActivityDiagram(activityDiagram);
            existingApi.setClassDiagram(classDiagram);
            return convertToDTO(apiRepository.save(existingApi));
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO updateTechnicalRequirementsAndBusinessProcessAndUserRequirements(Long id, String technicalRequirements, String businessProcess, String userRequirements) {
        Optional<Api> existingApiOptional = apiRepository.findById(id);
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            existingApi.setTechnicalRequirements(technicalRequirements);
            existingApi.setBusinessProcess(businessProcess);
            existingApi.setUserRequirements(userRequirements);
            return convertToDTO(apiRepository.save(existingApi));
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO updateInstallationGuide(Long id, String installationGuide) {
        Optional<Api> existingApiOptional = apiRepository.findById(id);
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            existingApi.setInstallationGuide(installationGuide);
            return convertToDTO(apiRepository.save(existingApi));
        } else {
            return null;
        }
    }

    @Override
    public ApiDTO updateEnvironmentId(Long id, Long environmentId) {
        Optional<Api> existingApiOptional = apiRepository.findById(id);
        if (existingApiOptional.isPresent()) {
            Api existingApi = existingApiOptional.get();
            existingApi.setEnvironmentId(environmentId);
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
    public List<ApiDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable) {
        return apiRepository.findByProjectIdAndStatus(projectId, status, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long countByProjectId(Long projectId) {
        return apiRepository.countByProjectId(projectId);
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
        apiDTO.setStatus(api.getStatus());
        apiDTO.setParameters(api.getParameters());
        apiDTO.setBodyJson(api.getBodyJson());
        apiDTO.setToken(api.getToken());
        apiDTO.setEnvironmentId(api.getEnvironmentId());
        apiDTO.setActivityDiagram(api.getActivityDiagram());
        apiDTO.setBusinessProcess(api.getBusinessProcess());
        apiDTO.setClassDiagram(api.getClassDiagram());
        apiDTO.setSequenceDiagram(api.getSequenceDiagram());
        apiDTO.setTechnicalRequirements(api.getTechnicalRequirements());
        apiDTO.setUseCaseDiagram(api.getUseCaseDiagram());
        apiDTO.setUserRequirements(api.getUserRequirements());
        apiDTO.setInstallationGuide(api.getInstallationGuide());
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
        api.setStatus(apiDTO.getStatus());
        api.setParameters(apiDTO.getParameters());
        api.setBodyJson(apiDTO.getBodyJson());
        api.setEnvironmentId(apiDTO.getEnvironmentId());
        api.setToken(apiDTO.getToken());
        api.setActivityDiagram(apiDTO.getActivityDiagram());
        api.setBusinessProcess(apiDTO.getBusinessProcess());
        api.setClassDiagram(apiDTO.getClassDiagram());
        api.setSequenceDiagram(apiDTO.getSequenceDiagram());
        api.setTechnicalRequirements(apiDTO.getTechnicalRequirements());
        api.setUseCaseDiagram(apiDTO.getUseCaseDiagram());
        api.setUserRequirements(apiDTO.getUserRequirements());
        api.setInstallationGuide(apiDTO.getInstallationGuide());
        api.setLifeCycle(apiDTO.getLifeCycle());
        return api;
    }
}