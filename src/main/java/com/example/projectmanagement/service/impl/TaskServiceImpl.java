package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.TaskDTO;
import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.repository.TaskRepository;
import com.example.projectmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDTO create(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        return convertToDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskDTO.getId());
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setProjectId(taskDTO.getProjectId());
            existingTask.setExecutorId(taskDTO.getExecutorId());
            existingTask.setName(taskDTO.getName());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setStatus(taskDTO.getStatus());
            existingTask.setPriority(taskDTO.getPriority());
            existingTask.setType(taskDTO.getType());
            existingTask.setTags(taskDTO.getTags());
            existingTask.setNotes(taskDTO.getNotes());
            existingTask.setStartDate(taskDTO.getStartDate());
            existingTask.setEndDate(taskDTO.getEndDate());
            existingTask.setDueDate(taskDTO.getDueDate());
            existingTask.setCompletionDate(taskDTO.getCompletionDate());
            existingTask.setEstimatedTime(taskDTO.getEstimatedTime());
            existingTask.setSpentTime(taskDTO.getSpentTime());
            existingTask.setRemainingTime(taskDTO.getRemainingTime());
            existingTask.setProgress(taskDTO.getProgress());
            existingTask.setCreatedBy(taskDTO.getCreatedBy());
            existingTask.setCreatedAt(taskDTO.getCreatedAt());
            existingTask.setUpdatedAt(taskDTO.getUpdatedAt());
            existingTask.setDeletedAt(taskDTO.getDeletedAt());
            existingTask.setDeletedBy(taskDTO.getDeletedBy());
            existingTask.setDeletedReason(taskDTO.getDeletedReason());
            existingTask.setDeletedNote(taskDTO.getDeletedNote());
            return convertToDTO(taskRepository.save(existingTask));
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO delete(TaskDTO taskDTO) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskDTO.getId());
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            taskRepository.delete(existingTask);
            return convertToDTO(existingTask);
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        return existingTaskOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<TaskDTO> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByName(String name, Pageable pageable) {
        return taskRepository.findByName(name, pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByProjectId(Long projectId, Pageable pageable) {
        return taskRepository.findByProjectId(projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByExecutorId(Long executorId, Pageable pageable) {
        return taskRepository.findByExecutorId(executorId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable) {
        return taskRepository.findByProjectIdAndStatus(projectId, status, pageable).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return taskRepository.count();
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setProjectId(task.getProjectId());
        taskDTO.setExecutorId(task.getExecutorId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setType(task.getType());
        taskDTO.setTags(task.getTags());
        taskDTO.setNotes(task.getNotes());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setCompletionDate(task.getCompletionDate());
        taskDTO.setEstimatedTime(task.getEstimatedTime());
        taskDTO.setSpentTime(task.getSpentTime());
        taskDTO.setRemainingTime(task.getRemainingTime());
        taskDTO.setProgress(task.getProgress());
        taskDTO.setCreatedBy(task.getCreatedBy());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUpdatedAt(task.getUpdatedAt());
        taskDTO.setDeletedAt(task.getDeletedAt());
        taskDTO.setDeletedBy(task.getDeletedBy());
        taskDTO.setDeletedReason(task.getDeletedReason());
        taskDTO.setDeletedNote(task.getDeletedNote());
        return taskDTO;
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setProjectId(taskDTO.getProjectId());
        task.setExecutorId(taskDTO.getExecutorId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setType(taskDTO.getType());
        task.setTags(taskDTO.getTags());
        task.setNotes(taskDTO.getNotes());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setDueDate(taskDTO.getDueDate());
        task.setCompletionDate(taskDTO.getCompletionDate());
        task.setEstimatedTime(taskDTO.getEstimatedTime());
        task.setSpentTime(taskDTO.getSpentTime());
        task.setRemainingTime(taskDTO.getRemainingTime());
        task.setProgress(taskDTO.getProgress());
        task.setCreatedBy(taskDTO.getCreatedBy());
        task.setCreatedAt(taskDTO.getCreatedAt());
        task.setUpdatedAt(taskDTO.getUpdatedAt());
        task.setDeletedAt(taskDTO.getDeletedAt());
        task.setDeletedBy(taskDTO.getDeletedBy());
        task.setDeletedReason(taskDTO.getDeletedReason());
        task.setDeletedNote(taskDTO.getDeletedNote());
        return task;
    }
}
