package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.RelatedDatabaseTableDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RelatedDatabaseTableService {
    RelatedDatabaseTableDTO create(RelatedDatabaseTableDTO relatedDatabaseTableDTO);
    RelatedDatabaseTableDTO update(RelatedDatabaseTableDTO relatedDatabaseTableDTO);
    void delete(Long id);
    RelatedDatabaseTableDTO findById(Long id);
    List<RelatedDatabaseTableDTO> findAllByApiId(Long apiId, Pageable pageable);
}