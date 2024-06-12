package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.RelatedDatabaseTableDTO;
import com.example.projectmanagement.entity.RelatedDatabaseTable;
import com.example.projectmanagement.repository.DatabaseTableRepository;
import com.example.projectmanagement.repository.RelatedDatabaseTableRepository;
import com.example.projectmanagement.service.RelatedDatabaseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatedDatabaseTableServiceImpl implements RelatedDatabaseTableService {

    @Autowired
    private RelatedDatabaseTableRepository relatedDatabaseTableRepository;

    @Autowired
    private DatabaseTableRepository databaseTableRepository;

    @Override
    public RelatedDatabaseTableDTO create(RelatedDatabaseTableDTO relatedDatabaseTableDTO) {
        RelatedDatabaseTable relatedDatabaseTable = convertToEntity(relatedDatabaseTableDTO);
        relatedDatabaseTable.setDatabaseTableId(databaseTableRepository.findByUuid(relatedDatabaseTableDTO.getDatabaseTableUuid()).orElseThrow(null).getId());
        relatedDatabaseTable.setDatabaseTableName(databaseTableRepository.findByUuid(relatedDatabaseTableDTO.getDatabaseTableUuid()).orElseThrow(null).getName());
        return convertToDTO(relatedDatabaseTableRepository.save(relatedDatabaseTable));
    }

    @Override
    public RelatedDatabaseTableDTO update(RelatedDatabaseTableDTO relatedDatabaseTableDTO) {
        Optional<RelatedDatabaseTable> existingRelatedDatabaseTableOptional = relatedDatabaseTableRepository.findById(relatedDatabaseTableDTO.getId());
        if (existingRelatedDatabaseTableOptional.isPresent()) {
            RelatedDatabaseTable existingRelatedDatabaseTable = existingRelatedDatabaseTableOptional.get();
            existingRelatedDatabaseTable.setApiId(relatedDatabaseTableDTO.getApiId());
            existingRelatedDatabaseTable.setDatabaseTableUuid(relatedDatabaseTableDTO.getDatabaseTableUuid());
            existingRelatedDatabaseTable.setDatabaseTableId(databaseTableRepository.findByUuid(relatedDatabaseTableDTO.getDatabaseTableUuid()).orElseThrow(null).getId());
            existingRelatedDatabaseTable.setDatabaseTableName(databaseTableRepository.findByUuid(relatedDatabaseTableDTO.getDatabaseTableUuid()).orElseThrow(null).getName());
            existingRelatedDatabaseTable.setDescription(relatedDatabaseTableDTO.getDescription());
            return convertToDTO(relatedDatabaseTableRepository.save(existingRelatedDatabaseTable));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Optional<RelatedDatabaseTable> existingRelatedDatabaseTableOptional = relatedDatabaseTableRepository.findById(id);
        existingRelatedDatabaseTableOptional.ifPresent(relatedDatabaseTableRepository::delete);
    }

    @Override
    public RelatedDatabaseTableDTO findById(Long id) {
        Optional<RelatedDatabaseTable> existingRelatedDatabaseTableOptional = relatedDatabaseTableRepository.findById(id);
        return existingRelatedDatabaseTableOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<RelatedDatabaseTableDTO> findAllByApiId(Long apiId, Pageable pageable) {
        return relatedDatabaseTableRepository.findAllByApiId(apiId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private RelatedDatabaseTableDTO convertToDTO(RelatedDatabaseTable relatedDatabaseTable) {
        RelatedDatabaseTableDTO relatedDatabaseTableDTO = new RelatedDatabaseTableDTO();
        relatedDatabaseTableDTO.setId(relatedDatabaseTable.getId());
        relatedDatabaseTableDTO.setApiId(relatedDatabaseTable.getApiId());
        relatedDatabaseTableDTO.setDatabaseTableUuid((relatedDatabaseTable.getDatabaseTableUuid()));
        relatedDatabaseTableDTO.setDatabaseTableName(relatedDatabaseTable.getDatabaseTableName());
        relatedDatabaseTableDTO.setDescription(relatedDatabaseTable.getDescription());
        return relatedDatabaseTableDTO;
    }

    private RelatedDatabaseTable convertToEntity(RelatedDatabaseTableDTO relatedDatabaseTableDTO) {
        RelatedDatabaseTable relatedDatabaseTable = new RelatedDatabaseTable();
        relatedDatabaseTable.setId(relatedDatabaseTableDTO.getId());
        relatedDatabaseTable.setApiId(relatedDatabaseTableDTO.getApiId());
        relatedDatabaseTable.setDatabaseTableUuid(relatedDatabaseTableDTO.getDatabaseTableUuid());
        relatedDatabaseTable.setDatabaseTableName(relatedDatabaseTableDTO.getDatabaseTableName());
        relatedDatabaseTable.setDescription(relatedDatabaseTableDTO.getDescription());
        return relatedDatabaseTable;
    }
}