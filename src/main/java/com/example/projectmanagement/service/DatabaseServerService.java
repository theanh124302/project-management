package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DatabaseServerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DatabaseServerService {
    DatabaseServerDTO create(DatabaseServerDTO databaseServerDTO);
    DatabaseServerDTO update(DatabaseServerDTO databaseServerDTO);
    DatabaseServerDTO delete(Long id);
    DatabaseServerDTO findById(Long id);
    Long count();
    List<DatabaseServerDTO> findAllByProjectId(Long projectId, Pageable pageable);
}
