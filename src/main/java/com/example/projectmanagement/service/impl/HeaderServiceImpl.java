package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.HeaderDTO;
import com.example.projectmanagement.entity.Header;
import com.example.projectmanagement.repository.HeaderRepository;
import com.example.projectmanagement.service.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeaderServiceImpl implements HeaderService {
    @Autowired
    private HeaderRepository headerRepository;

    public HeaderDTO create(HeaderDTO headerDTO) {
        Header header = convertToEntity(headerDTO);
        return convertToDTO(headerRepository.save(header));
    }

    public HeaderDTO update(HeaderDTO headerDTO) {
        Optional<Header> existingHeaderOptional = headerRepository.findById(headerDTO.getId());
        if (existingHeaderOptional.isPresent()) {
            Header existingHeader = existingHeaderOptional.get();
            existingHeader.setHeaderKey(headerDTO.getHeaderKey());
            existingHeader.setType(headerDTO.getType());
            existingHeader.setDescription(headerDTO.getDescription());
            existingHeader.setSample(headerDTO.getSample());
            existingHeader.setApiId(headerDTO.getApiId());
            return convertToDTO(headerRepository.save(existingHeader));
        } else {
            return null;
        }
    }

    public HeaderDTO delete(Long id) {
        Optional<Header> existingHeaderOptional = headerRepository.findById(id);
        if (existingHeaderOptional.isPresent()) {
            Header existingHeader = existingHeaderOptional.get();
            headerRepository.delete(existingHeader);
            return convertToDTO(existingHeader);
        } else {
            return null;
        }
    }

    public HeaderDTO findById(Long id) {
        Optional<Header> existingHeaderOptional = headerRepository.findById(id);
        return existingHeaderOptional.map(this::convertToDTO).orElse(null);
    }

    public List<HeaderDTO> findByApiId(Long apiId, Pageable pageable) {
        return headerRepository.findByApiId(apiId, pageable).stream().map(this::convertToDTO).toList();
    }

    public Long count() {
        return headerRepository.count();
    }

    private Header convertToEntity(HeaderDTO headerDTO) {
        Header header = new Header();
        header.setId(headerDTO.getId());
        header.setHeaderKey(headerDTO.getHeaderKey());
        header.setType(headerDTO.getType());
        header.setDescription(headerDTO.getDescription());
        header.setSample(headerDTO.getSample());
        header.setApiId(headerDTO.getApiId());
        return header;
    }

    private HeaderDTO convertToDTO(Header header) {
        HeaderDTO headerDTO = new HeaderDTO();
        headerDTO.setId(header.getId());
        headerDTO.setHeaderKey(header.getHeaderKey());
        headerDTO.setType(header.getType());
        headerDTO.setDescription(header.getDescription());
        headerDTO.setSample(header.getSample());
        headerDTO.setApiId(header.getApiId());
        return headerDTO;
    }


}
