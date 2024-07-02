package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.BarChartDTO;
import com.example.projectmanagement.dto.ChartDTO;
import com.example.projectmanagement.dto.TaskDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskDTO create(TaskDTO taskDTO);
    TaskDTO update(TaskDTO taskDTO);
    TaskDTO delete(TaskDTO taskDTO);
    TaskDTO findById(Long id);
    TaskDTO assignTask(Long taskId, Long userId, Long assignerId);
    TaskDTO assignTaskByUsername(Long taskId, String username, Long assignerId);
    TaskDTO unassignTask(Long taskId, Long userId, Long unAssignerId);
    TaskDTO unassignTaskByUsername(Long taskId, String username, Long unAssignerId);
    TaskDTO addReviewer(Long taskId, Long userId, Long adderId);
    TaskDTO addReviewerByUsername(Long taskId, String username, Long adderId);
    TaskDTO removeReviewer(Long taskId, Long removerId);
    TaskDTO removeReviewerByUsername(Long taskId, String username);
    List<TaskDTO> getAllTasks(Pageable pageable);
    List<TaskDTO> findByName(String name, Pageable pageable);
    List<TaskDTO> findByProjectId(Long projectId, Pageable pageable);
    List<TaskDTO> findByUserId(Long userId);
    List<TaskDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable);
    List<TaskDTO> findByProjectIdAndName(Long projectId, String name, Pageable pageable);
    List<TaskDTO> findByUserIdAndProjectId(Long userId, Long projectId, Pageable pageable);
    List<TaskDTO> findByUserIdAndProjectIdAndName(Long userId, Long projectId, String name, Pageable pageable);
    List<TaskDTO> findByUserIdAndProjectIdAndNameAndStatus(Long userId, Long projectId, String name, String status, Pageable pageable);
    List<TaskDTO> findByProjectIdAndNameAndStatus(Long projectId, String name, String status, Pageable pageable);
    Long countByProjectIdAndStatus(Long projectId, String status);
    Long countByProjectId(Long projectId);
    List<ChartDTO> countByProjectIdGroupByStatus(Long projectId);
    List<BarChartDTO> countDueDateByDay(Long projectId);
    List<BarChartDTO> countDueDateByDayAndUserId(Long userId);
    List<BarChartDTO> countDueDateByMonth(Long projectId);
    Long count();
}
