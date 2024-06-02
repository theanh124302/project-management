package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DocsDTO;
import com.example.projectmanagement.entity.Docs;
import com.example.projectmanagement.repository.DocsRepository;
import com.example.projectmanagement.service.DocsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocsServiceImpl implements DocsService {

    @Autowired
    private DocsRepository docsRepository;

    @Override
    public DocsDTO create(DocsDTO docsDTO) {
        Docs docs = convertToEntity(docsDTO);
        return convertToDTO(docsRepository.save(docs));
    }

    @Override
    public DocsDTO update(DocsDTO docsDTO) {
        Optional<Docs> existingDocsOptional = docsRepository.findById(docsDTO.getId());
        if (existingDocsOptional.isPresent()) {
            Docs existingDocs = existingDocsOptional.get();
            existingDocs.setDescription(docsDTO.getDescription());
            existingDocs.setApiId(docsDTO.getApiId());
            existingDocs.setUrl(docsDTO.getUrl());
            return convertToDTO(docsRepository.save(existingDocs));
        } else {
            return null;
        }
    }

    @Override
    public DocsDTO delete(Long id, Long deletedBy) {
        Optional<Docs> existingDocsOptional = docsRepository.findById(id);
        if (existingDocsOptional.isPresent()) {
            Docs existingDocs = existingDocsOptional.get();
            docsRepository.delete(existingDocs);
            return convertToDTO(existingDocs);
        } else {
            return null;
        }
    }

    @Override
    public DocsDTO findById(Long id) {
        Optional<Docs> existingDocsOptional = docsRepository.findById(id);
        return existingDocsOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DocsDTO> findByApiId(Long apiId, Pageable pageable) {
        return docsRepository.findByApiId(apiId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return docsRepository.count();
    }

    private DocsDTO convertToDTO(Docs docs) {
        DocsDTO docsDTO = new DocsDTO();
        docsDTO.setId(docs.getId());
        docsDTO.setDescription(docs.getDescription());
        docsDTO.setUrl(docs.getUrl());
        docsDTO.setApiId(docs.getApiId());
        return docsDTO;
    }

    private Docs convertToEntity(DocsDTO docsDTO) {
        Docs docs = new Docs();
        docs.setId(docsDTO.getId());
        docs.setDescription(docsDTO.getDescription());
        docs.setUrl(docsDTO.getUrl());
        docs.setApiId(docsDTO.getApiId());
        return docs;
    }
}