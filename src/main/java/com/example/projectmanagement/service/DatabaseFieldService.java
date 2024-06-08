package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DatabaseFieldDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DatabaseFieldService {
    DatabaseFieldDTO create(DatabaseFieldDTO databaseFieldDTO);
    DatabaseFieldDTO update(DatabaseFieldDTO databaseFieldDTO);
    DatabaseFieldDTO delete(Long id);
    DatabaseFieldDTO findById(Long id);
    List<DatabaseFieldDTO> findByDatabaseTableId(Long databaseTableId, Pageable pageable);
}
