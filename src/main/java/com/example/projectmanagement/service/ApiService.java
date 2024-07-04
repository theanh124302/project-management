package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ApiDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApiService {
    ApiDTO create(ApiDTO apiDTO);
    ApiDTO update(ApiDTO apiDTO);
    ApiDTO updateUrlAndMethod(Long id, String url, String method);
    ApiDTO updateParametersAndBodyAndTokenAndHeader(Long id, String parameters, String body, String token, String header);
    ApiDTO updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagram(Long id, String useCaseDiagram, String sequenceDiagram, String activityDiagram, String classDiagram);
    ApiDTO updateTechnicalRequirementsAndBusinessProcessAndUserRequirements(Long id, String technicalRequirements, String businessProcess, String userRequirements);
    ApiDTO updateInstallationGuide(Long id, String installationGuide);
    ApiDTO updateEnvironmentId(Long id, Long environmentId);
    ApiDTO delete(ApiDTO apiDTO);
    ApiDTO findById(Long id);
    List<ApiDTO> findByProjectId(Long projectId, Pageable pageable);
    List<ApiDTO> getAllApis(Pageable pageable);
    List<ApiDTO> findByName(String name, Pageable pageable);
    List<ApiDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable);
    List<ApiDTO> findByFolderId(Long folderId, Pageable pageable);
    Long count();
}
