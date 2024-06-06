package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DesignResponseDTO;
import com.example.projectmanagement.entity.DesignResponse;
import com.example.projectmanagement.repository.DesignResponseRepository;
import com.example.projectmanagement.service.DesignResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DesignResponseServiceImpl implements DesignResponseService {

    @Autowired
    private DesignResponseRepository designResponseRepository;

    @Override
    public DesignResponseDTO create(DesignResponseDTO designResponseDTO) {
        DesignResponse designResponse = convertToEntity(designResponseDTO);
        return convertToDTO(designResponseRepository.save(designResponse));
    }

    @Override
    public DesignResponseDTO update(DesignResponseDTO designResponseDTO) {
        Optional<DesignResponse> existingDesignResponseOptional = designResponseRepository.findById(designResponseDTO.getId());
        if (existingDesignResponseOptional.isPresent()) {
            DesignResponse existingDesignResponse = existingDesignResponseOptional.get();
            existingDesignResponse.setId(designResponseDTO.getId());
            existingDesignResponse.setValue(designResponseDTO.getValue());
            existingDesignResponse.setDescription(designResponseDTO.getDescription());
            existingDesignResponse.setApiId(designResponseDTO.getApiId());
            return convertToDTO(designResponseRepository.save(existingDesignResponse));
        } else {
            return null;
        }
    }

    @Override
    public DesignResponseDTO delete(Long id) {
        Optional<DesignResponse> existingDesignResponseOptional = designResponseRepository.findById(id);
        if (existingDesignResponseOptional.isPresent()) {
            DesignResponse existingDesignResponse = existingDesignResponseOptional.get();
            designResponseRepository.delete(existingDesignResponse);
            return convertToDTO(existingDesignResponse);
        } else {
            return null;
        }
    }

    @Override
    public DesignResponseDTO findById(Long id) {
        Optional<DesignResponse> existingDesignResponseOptional = designResponseRepository.findById(id);
        return existingDesignResponseOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DesignResponseDTO> findByApiId(Long apiId, Pageable pageable) {
        return designResponseRepository.findByApiId(apiId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return designResponseRepository.count();
    }

    private DesignResponse convertToEntity(DesignResponseDTO designResponseDTO) {
        DesignResponse designResponse = new DesignResponse();
        designResponse.setId(designResponseDTO.getId());
        designResponse.setValue(designResponseDTO.getValue());
        designResponse.setDescription(designResponseDTO.getDescription());
        designResponse.setApiId(designResponseDTO.getApiId());
        return designResponse;
    }

    private DesignResponseDTO convertToDTO(DesignResponse designResponse) {
        DesignResponseDTO designResponseDTO = new DesignResponseDTO();
        designResponseDTO.setId(designResponse.getId());
        designResponseDTO.setValue(designResponse.getValue());
        designResponseDTO.setDescription(designResponse.getDescription());
        designResponseDTO.setApiId(designResponse.getApiId());
        return designResponseDTO;
    }


}
