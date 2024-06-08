package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DatabaseFieldDTO;
import com.example.projectmanagement.entity.DatabaseField;
import com.example.projectmanagement.repository.DatabaseFieldRepository;
import com.example.projectmanagement.service.DatabaseFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatabaseFieldServiceImpl implements DatabaseFieldService {

    @Autowired
    private DatabaseFieldRepository databaseFieldRepository;

    @Override
    public DatabaseFieldDTO create(DatabaseFieldDTO databaseFieldDTO) {
        DatabaseField databaseField = convertToEntity(databaseFieldDTO);
        return convertToDTO(databaseFieldRepository.save(databaseField));
    }

    @Override
    public DatabaseFieldDTO update(DatabaseFieldDTO databaseFieldDTO) {
        Optional<DatabaseField> existingDatabaseFieldOptional = databaseFieldRepository.findById(databaseFieldDTO.getId());
        if (existingDatabaseFieldOptional.isPresent()) {
            DatabaseField existingDatabaseField = existingDatabaseFieldOptional.get();
            existingDatabaseField.setId(databaseFieldDTO.getId());
            existingDatabaseField.setDatabaseTableId(databaseFieldDTO.getDatabaseTableId());
            existingDatabaseField.setFieldName(databaseFieldDTO.getFieldName());
            existingDatabaseField.setDescription(databaseFieldDTO.getDescription());
            existingDatabaseField.setType(databaseFieldDTO.getType());
            existingDatabaseField.setSample(databaseFieldDTO.getSample());
            return convertToDTO(databaseFieldRepository.save(existingDatabaseField));
        } else {
            return null;
        }
    }

    @Override
    public DatabaseFieldDTO delete(Long id) {
        Optional<DatabaseField> existingDatabaseFieldOptional = databaseFieldRepository.findById(id);
        if (existingDatabaseFieldOptional.isPresent()) {
            DatabaseField existingDatabaseField = existingDatabaseFieldOptional.get();
            databaseFieldRepository.delete(existingDatabaseField);
            return convertToDTO(existingDatabaseField);
        } else {
            return null;
        }
    }

    @Override
    public DatabaseFieldDTO findById(Long id) {
        Optional<DatabaseField> databaseFieldOptional = databaseFieldRepository.findById(id);
        return databaseFieldOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DatabaseFieldDTO> findByDatabaseTableId(Long databaseTableId, Pageable pageable) {
        return databaseFieldRepository.findAllByDatabaseTableId(databaseTableId, pageable).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DatabaseFieldDTO convertToDTO(DatabaseField databaseField) {
        DatabaseFieldDTO databaseFieldDTO = new DatabaseFieldDTO();
        databaseFieldDTO.setId(databaseField.getId());
        databaseFieldDTO.setDatabaseTableId(databaseField.getDatabaseTableId());
        databaseFieldDTO.setFieldName(databaseField.getFieldName());
        databaseFieldDTO.setDescription(databaseField.getDescription());
        databaseFieldDTO.setType(databaseField.getType());
        databaseFieldDTO.setSample(databaseField.getSample());
        return databaseFieldDTO;
    }

    private DatabaseField convertToEntity(DatabaseFieldDTO databaseFieldDTO) {
        DatabaseField databaseField = new DatabaseField();
        databaseField.setId(databaseFieldDTO.getId());
        databaseField.setDatabaseTableId(databaseFieldDTO.getDatabaseTableId());
        databaseField.setFieldName(databaseFieldDTO.getFieldName());
        databaseField.setDescription(databaseFieldDTO.getDescription());
        databaseField.setType(databaseFieldDTO.getType());
        databaseField.setSample(databaseFieldDTO.getSample());
        return databaseField;
    }
}
