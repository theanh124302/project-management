package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DatabaseTableDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DatabaseTableService {
    DatabaseTableDTO create(DatabaseTableDTO databaseTableDTO);
    DatabaseTableDTO update(DatabaseTableDTO databaseTableDTO);
    DatabaseTableDTO delete(Long id);
    DatabaseTableDTO findById(Long id);
    List<DatabaseTableDTO> findAllByDatabaseServerId(Long databaseServerId, Pageable pageable);
}
