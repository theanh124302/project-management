package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DocsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocsService {
    DocsDTO create(DocsDTO docsDTO);
    DocsDTO update(DocsDTO docsDTO);
    DocsDTO delete(Long id, Long deletedBy);
    DocsDTO findById(Long id);
    List<DocsDTO> findByApiId(Long apiId, Pageable pageable);
    Long count();
}
