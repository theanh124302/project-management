package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DefineRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DefineRequestService {
    DefineRequestDTO create(DefineRequestDTO defineRequestDTO);
    DefineRequestDTO update(DefineRequestDTO defineRequestDTO);
    DefineRequestDTO delete(Long id);
    DefineRequestDTO findById(Long id);
    List<DefineRequestDTO> findByProjectId(Long projectId, Pageable pageable);
    List<DefineRequestDTO> findByUserId(Long userId, Pageable pageable);
    List<DefineRequestDTO> findByTaskId(Long taskId, Pageable pageable);
    Long count();
}
