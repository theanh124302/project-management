package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.TaskDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskDTO create(TaskDTO taskDTO);
    TaskDTO update(TaskDTO taskDTO);
    TaskDTO delete(TaskDTO taskDTO);
    TaskDTO findById(Long id);
    TaskDTO assignTask(Long taskId, Long userId);
    TaskDTO assignTaskByUsername(Long taskId, String username);
    TaskDTO unassignTask(Long taskId, Long userId);
    TaskDTO unassignTaskByUsername(Long taskId, String username);
    List<TaskDTO> getAllTasks(Pageable pageable);
    List<TaskDTO> findByName(String name, Pageable pageable);
    List<TaskDTO> findByProjectId(Long projectId, Pageable pageable);
    List<TaskDTO> findByExecutorId(Long executorId, Pageable pageable);
    List<TaskDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable);
    Long count();
}
