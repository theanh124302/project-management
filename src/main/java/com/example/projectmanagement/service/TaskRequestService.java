package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.TaskRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskRequestService {
    TaskRequestDTO create(TaskRequestDTO taskRequestDTO);
    TaskRequestDTO update(TaskRequestDTO taskRequestDTO);
    TaskRequestDTO delete(Long id);
    TaskRequestDTO findById(Long id);
    List<TaskRequestDTO> findByProjectId(Long projectId, Pageable pageable);
    List<TaskRequestDTO> findByUserId(Long userId, Pageable pageable);
    List<TaskRequestDTO> findByTaskId(Long taskId, Pageable pageable);
    Long count();
}
