package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.HeaderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface HeaderService {
    HeaderDTO create(HeaderDTO headerDTO);
    HeaderDTO update(HeaderDTO headerDTO);
    HeaderDTO delete(Long id);
    HeaderDTO findById(Long id);
    List<HeaderDTO> findByApiId(Long apiId, Pageable pageable);
    Long count();
}
