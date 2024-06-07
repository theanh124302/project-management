package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DatabaseServerDTO;
import com.example.projectmanagement.entity.DatabaseServer;
import com.example.projectmanagement.repository.DatabaseServerRepository;
import com.example.projectmanagement.service.DatabaseServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class
DatabaseServerServiceImpl  implements DatabaseServerService {

    @Autowired
    private DatabaseServerRepository databaseServerRepository;

    @Override
    public DatabaseServerDTO create(DatabaseServerDTO databaseServerDTO) {
        DatabaseServer databaseServer = convertToEntity(databaseServerDTO);
        return convertToDTO(databaseServerRepository.save(databaseServer));
    }

    @Override
    public DatabaseServerDTO update(DatabaseServerDTO databaseServerDTO) {
        Optional<DatabaseServer> existingDatabaseServerOptional = databaseServerRepository.findById(databaseServerDTO.getId());
        if (existingDatabaseServerOptional.isPresent()) {
            DatabaseServer existingDatabaseServer = existingDatabaseServerOptional.get();
            existingDatabaseServer.setId(databaseServerDTO.getId());
            existingDatabaseServer.setDescription(databaseServerDTO.getDescription());
            existingDatabaseServer.setProjectId(databaseServerDTO.getProjectId());
            existingDatabaseServer.setName(databaseServerDTO.getName());
            existingDatabaseServer.setType(databaseServerDTO.getType());
            existingDatabaseServer.setUrl(databaseServerDTO.getUrl());
            existingDatabaseServer.setUsername(databaseServerDTO.getUsername());
            existingDatabaseServer.setPassword(databaseServerDTO.getPassword());
            return convertToDTO(databaseServerRepository.save(existingDatabaseServer));
        } else {
            return null;
        }
    }

    @Override
    public DatabaseServerDTO delete(Long id) {
        Optional<DatabaseServer> existingDatabaseServerOptional = databaseServerRepository.findById(id);
        if (existingDatabaseServerOptional.isPresent()) {
            DatabaseServer existingDatabaseServer = existingDatabaseServerOptional.get();
            databaseServerRepository.delete(existingDatabaseServer);
            return convertToDTO(existingDatabaseServer);
        } else {
            return null;
        }
    }

    @Override
    public DatabaseServerDTO findById(Long id) {
        Optional<DatabaseServer> existingDatabaseServerOptional = databaseServerRepository.findById(id);
        return existingDatabaseServerOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DatabaseServerDTO> findAllByProjectId(Long projectId, Pageable pageable) {
        return databaseServerRepository.findAllByProjectId(projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return databaseServerRepository.count();
    }

    private DatabaseServerDTO convertToDTO(DatabaseServer databaseServer) {
        DatabaseServerDTO databaseServerDTO = new DatabaseServerDTO();
        databaseServerDTO.setId(databaseServer.getId());
        databaseServerDTO.setDescription(databaseServer.getDescription());
        databaseServerDTO.setProjectId(databaseServer.getProjectId());
        databaseServerDTO.setName(databaseServer.getName());
        databaseServerDTO.setType(databaseServer.getType());
        databaseServerDTO.setUrl(databaseServer.getUrl());
        databaseServerDTO.setUsername(databaseServer.getUsername());
        databaseServerDTO.setPassword(databaseServer.getPassword());
        return databaseServerDTO;
    }

    private DatabaseServer convertToEntity(DatabaseServerDTO databaseServerDTO) {
        DatabaseServer databaseServer = new DatabaseServer();
        databaseServer.setId(databaseServerDTO.getId());
        databaseServer.setDescription(databaseServerDTO.getDescription());
        databaseServer.setProjectId(databaseServerDTO.getProjectId());
        databaseServer.setName(databaseServerDTO.getName());
        databaseServer.setType(databaseServerDTO.getType());
        databaseServer.setUrl(databaseServerDTO.getUrl());
        databaseServer.setUsername(databaseServerDTO.getUsername());
        databaseServer.setPassword(databaseServerDTO.getPassword());
        return databaseServer;
    }
}
