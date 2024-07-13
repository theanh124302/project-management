package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DocsDTO;
import com.example.projectmanagement.dto.DocumentStructureDTO;
import com.example.projectmanagement.entity.Docs;
import com.example.projectmanagement.entity.DocumentStructure;
import com.example.projectmanagement.repository.DocsRepository;
import com.example.projectmanagement.repository.DocumentStructureRepository;
import com.example.projectmanagement.service.DocsService;
import com.example.projectmanagement.service.DocumentStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentStructureServiceImpl implements DocumentStructureService {

    @Autowired
    private DocumentStructureRepository documentStructureRepository;

    @Override
    public DocumentStructureDTO create(DocumentStructureDTO documentStructureDTO) {
        DocumentStructure documentStructure = convertToEntity(documentStructureDTO);
        return convertToDTO(documentStructureRepository.save(documentStructure));
    }

    @Override
    public DocumentStructureDTO update(DocumentStructureDTO documentStructureDTO) {
        Optional<DocumentStructure> existingDocumentStructureOptional = documentStructureRepository.findById(documentStructureDTO.getId());
        if (existingDocumentStructureOptional.isPresent()) {
            DocumentStructure existingDocumentStructure = existingDocumentStructureOptional.get();
            existingDocumentStructure.setProjectId(documentStructureDTO.getProjectId());
            existingDocumentStructure.setName(documentStructureDTO.getName());
            existingDocumentStructure.setContent(documentStructureDTO.getContent());
            existingDocumentStructure.setDescription(documentStructureDTO.getDescription());
            return convertToDTO(documentStructureRepository.save(existingDocumentStructure));
        } else {
            return null;
        }
    }

    @Override
    public DocumentStructureDTO delete(Long id) {
        Optional<DocumentStructure> existingDocumentStructureOptional = documentStructureRepository.findById(id);
        if (existingDocumentStructureOptional.isPresent()) {
            DocumentStructure existingDocumentStructure = existingDocumentStructureOptional.get();
            documentStructureRepository.delete(existingDocumentStructure);
            return convertToDTO(existingDocumentStructure);
        } else {
            return null;
        }
    }

    @Override
    public DocumentStructureDTO findById(Long id) {
        Optional<DocumentStructure> existingDocumentStructureOptional = documentStructureRepository.findById(id);
        return existingDocumentStructureOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DocumentStructureDTO> findByProjectId(Long projectId) {
        List<DocumentStructure> existingDocumentStructureOptional = documentStructureRepository.findByProjectId(projectId);
        return existingDocumentStructureOptional.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private DocumentStructureDTO convertToDTO(DocumentStructure documentStructure) {
        DocumentStructureDTO documentStructureDTO = new DocumentStructureDTO();
        documentStructureDTO.setId(documentStructure.getId());
        documentStructureDTO.setProjectId(documentStructure.getProjectId());
        documentStructureDTO.setName(documentStructure.getName());
        documentStructureDTO.setContent(documentStructure.getContent());
        documentStructureDTO.setDescription(documentStructure.getDescription());
        return documentStructureDTO;
    }

    private DocumentStructure convertToEntity(DocumentStructureDTO documentStructureDTO) {
        DocumentStructure documentStructure = new DocumentStructure();
        documentStructure.setId(documentStructureDTO.getId());
        documentStructure.setProjectId(documentStructureDTO.getProjectId());
        documentStructure.setName(documentStructureDTO.getName());
        documentStructure.setContent(documentStructureDTO.getContent());
        documentStructure.setDescription(documentStructureDTO.getDescription());
        return documentStructure;
    }
}