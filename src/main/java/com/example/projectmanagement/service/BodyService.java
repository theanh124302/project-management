package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.BodyDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BodyService {
    BodyDTO create(BodyDTO bodyDTO);
    BodyDTO update(BodyDTO bodyDTO);
    BodyDTO delete(Long id, Long deletedBy);
    BodyDTO findById(Long id);
    Long count();
    List<BodyDTO> findByApiId(Long apiId, Pageable pageable);
}
