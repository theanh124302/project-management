package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DatabaseTableDTO;
import com.example.projectmanagement.entity.DatabaseTable;
import com.example.projectmanagement.repository.DatabaseTableRepository;
import com.example.projectmanagement.service.DatabaseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatabaseTableServiceImpl implements DatabaseTableService {

    @Autowired
    private DatabaseTableRepository databaseTableRepository;

    @Override
    public DatabaseTableDTO create(DatabaseTableDTO databaseTableDTO) {
        DatabaseTable databaseTable = convertToEntity(databaseTableDTO);
        return convertToDTO(databaseTableRepository.save(databaseTable));
    }

    @Override
    public DatabaseTableDTO update(DatabaseTableDTO databaseTableDTO) {
        Optional<DatabaseTable> existingDatabaseTableOptional = databaseTableRepository.findById(databaseTableDTO.getId());
        if (existingDatabaseTableOptional.isPresent()) {
            DatabaseTable existingDatabaseTable = existingDatabaseTableOptional.get();
            existingDatabaseTable.setId(databaseTableDTO.getId());
            existingDatabaseTable.setDatabaseServerId(databaseTableDTO.getDatabaseServerId());
            existingDatabaseTable.setName(databaseTableDTO.getName());
            existingDatabaseTable.setDescription(databaseTableDTO.getDescription());
            return convertToDTO(databaseTableRepository.save(existingDatabaseTable));
        } else {
            return null;
        }
    }

    @Override
    public DatabaseTableDTO delete(Long id) {
        Optional<DatabaseTable> existingDatabaseTableOptional = databaseTableRepository.findById(id);
        if (existingDatabaseTableOptional.isPresent()) {
            DatabaseTable existingDatabaseTable = existingDatabaseTableOptional.get();
            databaseTableRepository.delete(existingDatabaseTable);
            return convertToDTO(existingDatabaseTable);
        } else {
            return null;
        }
    }

    @Override
    public DatabaseTableDTO findById(Long id) {
        Optional<DatabaseTable> databaseTableOptional = databaseTableRepository.findById(id);
        return databaseTableOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DatabaseTableDTO> findAllByDatabaseServerId(Long databaseServerId, Pageable pageable) {
        return databaseTableRepository.findAllByDatabaseServerId(databaseServerId, pageable).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DatabaseTableDTO convertToDTO(DatabaseTable databaseTable) {
        DatabaseTableDTO databaseTableDTO = new DatabaseTableDTO();
        databaseTableDTO.setId(databaseTable.getId());
        databaseTableDTO.setDatabaseServerId(databaseTable.getDatabaseServerId());
        databaseTableDTO.setName(databaseTable.getName());
        databaseTableDTO.setDescription(databaseTable.getDescription());
        return databaseTableDTO;
    }

    private DatabaseTable convertToEntity(DatabaseTableDTO databaseTableDTO) {
        DatabaseTable databaseTable = new DatabaseTable();
        databaseTable.setId(databaseTableDTO.getId());
        databaseTable.setDatabaseServerId(databaseTableDTO.getDatabaseServerId());
        databaseTable.setName(databaseTableDTO.getName());
        databaseTable.setDescription(databaseTableDTO.getDescription());
        return databaseTable;
    }


}
