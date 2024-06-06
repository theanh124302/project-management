package com.example.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.projectmanagement.dto.ApiImpactDTO;
import com.example.projectmanagement.entity.ApiImpact;
import com.example.projectmanagement.repository.ApiImpactRepository;
import com.example.projectmanagement.service.ApiImpactService;


@Service
public class ApiImpactImpl implements ApiImpactService {

    @Autowired
    private ApiImpactRepository apiImpactRepository;

    @Override
    public ApiImpactDTO create(ApiImpactDTO apiImpactDTO) {
        ApiImpact apiImpact = convertToEntity(apiImpactDTO);
        return convertToDTO(apiImpactRepository.save(apiImpact));
    }

    @Override
    public ApiImpactDTO update(ApiImpactDTO apiImpactDTO) {
        Optional<ApiImpact> existingApiImpactOptional = apiImpactRepository.findById(apiImpactDTO.getId());
        if (existingApiImpactOptional.isPresent()) {
            ApiImpact existingApiImpact = existingApiImpactOptional.get();
            existingApiImpact.setApiId(apiImpactDTO.getApiId());
            existingApiImpact.setImpactApiId(apiImpactDTO.getImpactApiId());
            existingApiImpact.setApiImpactName(apiImpactDTO.getApiImpactName());
            existingApiImpact.setStatus(apiImpactDTO.getStatus());
            existingApiImpact.setImpactDescription(apiImpactDTO.getImpactDescription());
            existingApiImpact.setImpactPriority(apiImpactDTO.getImpactPriority());
            existingApiImpact.setSolution(apiImpactDTO.getSolution());
            return convertToDTO(apiImpactRepository.save(existingApiImpact));
        } else {
            return null;
        }
    }

    @Override
    public ApiImpactDTO delete(Long id) {
        Optional<ApiImpact> existingApiImpactOptional = apiImpactRepository.findById(id);
        if (existingApiImpactOptional.isPresent()) {
            ApiImpact existingApiImpact = existingApiImpactOptional.get();
            apiImpactRepository.delete(existingApiImpact);
            return convertToDTO(existingApiImpact);
        } else {
            return null;
        }
    }

    @Override
    public ApiImpactDTO findById(Long id) {
        Optional<ApiImpact> existingApiImpactOptional = apiImpactRepository.findById(id);
        return existingApiImpactOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<ApiImpactDTO> findByApiId(Long apiId, Pageable pageable) {
        return apiImpactRepository.findByApiId(apiId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return null;
    }

    private ApiImpactDTO convertToDTO(ApiImpact apiImpact) {
        ApiImpactDTO apiImpactDTO = new ApiImpactDTO();
        apiImpactDTO.setId(apiImpact.getId());
        apiImpactDTO.setApiId(apiImpact.getApiId());
        apiImpactDTO.setImpactApiId(apiImpact.getImpactApiId());
        apiImpactDTO.setApiImpactName(apiImpact.getApiImpactName());
        apiImpactDTO.setStatus(apiImpact.getStatus());
        apiImpactDTO.setImpactDescription(apiImpact.getImpactDescription());
        apiImpactDTO.setImpactPriority(apiImpact.getImpactPriority());
        apiImpactDTO.setSolution(apiImpact.getSolution());
        return apiImpactDTO;
    }

    private ApiImpact convertToEntity(ApiImpactDTO apiImpactDTO) {
        ApiImpact apiImpact = new ApiImpact();
        apiImpact.setId(apiImpactDTO.getId());
        apiImpact.setApiId(apiImpactDTO.getApiId());
        apiImpact.setImpactApiId(apiImpactDTO.getImpactApiId());
        apiImpact.setApiImpactName(apiImpactDTO.getApiImpactName());
        apiImpact.setStatus(apiImpactDTO.getStatus());
        apiImpact.setImpactDescription(apiImpactDTO.getImpactDescription());
        apiImpact.setImpactPriority(apiImpactDTO.getImpactPriority());
        apiImpact.setSolution(apiImpactDTO.getSolution());
        return apiImpact;
    }

}
