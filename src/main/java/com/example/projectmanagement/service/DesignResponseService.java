package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DesignResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DesignResponseService {
    DesignResponseDTO create(DesignResponseDTO designResponseDTO);
    DesignResponseDTO update(DesignResponseDTO designResponseDTO);
    DesignResponseDTO delete(Long id);
    DesignResponseDTO findById(Long id);
    List<DesignResponseDTO> findByApiId(Long apiId, Pageable pageable);
    Long count();
}
