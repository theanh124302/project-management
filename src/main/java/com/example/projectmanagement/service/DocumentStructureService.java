package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DocumentStructureDTO;

import java.util.List;

public interface DocumentStructureService {
    DocumentStructureDTO create(DocumentStructureDTO documentStructureDTO);
    DocumentStructureDTO update(DocumentStructureDTO documentStructureDTO);
    DocumentStructureDTO delete(Long id);
    DocumentStructureDTO findById(Long id);
    List<DocumentStructureDTO> findByProjectId(Long projectId);
}
