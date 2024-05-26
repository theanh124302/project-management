package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.UserTaskDTO;

import java.util.List;

public interface UserTaskService {
    UserTaskDTO create(UserTaskDTO userTaskDTO);
    UserTaskDTO update(UserTaskDTO userTaskDTO);
    UserTaskDTO delete(UserTaskDTO userTaskDTO);
    UserTaskDTO findById(Long id);
    List<UserTaskDTO> findByTaskIdAndUserId(Long taskId, Long userId);
    Long count();
}
