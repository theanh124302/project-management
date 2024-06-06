package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ApiImpactDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ApiImpactService {
    ApiImpactDTO create(ApiImpactDTO apiImpactDTO);
    ApiImpactDTO update(ApiImpactDTO apiImpactDTO);
    ApiImpactDTO delete(Long id);
    ApiImpactDTO findById(Long id);
    List<ApiImpactDTO> findByApiId(Long apiId, Pageable pageable);
    Long count();
}
