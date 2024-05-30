package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.TaskDTO;
import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.entity.UserTask;
import com.example.projectmanagement.enums.TaskStatus;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.TaskRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.repository.UserTaskRepository;
import com.example.projectmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;
    @Autowired
    private ProjectRepository projectRepository;

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
            existingTask.setProgress(taskDTO.getProgress());
            existingTask.setCreatedBy(taskDTO.getCreatedBy());
            existingTask.setCreatedAt(taskDTO.getCreatedAt());
            existingTask.setUpdatedAt(taskDTO.getUpdatedAt());
            existingTask.setCanceledAt(taskDTO.getCanceledAt());
            existingTask.setCanceledBy(taskDTO.getCanceledBy());
            existingTask.setCanceledReason(taskDTO.getCanceledReason());
            existingTask.setCanceledNote(taskDTO.getCanceledNote());

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
    public TaskDTO assignTask(Long taskId, Long userId, Long assignerId) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingTaskOptional.isPresent() && existingUserOptional.isPresent()) {
            if(assignerId.equals(projectRepository.findById(existingTaskOptional.get().getProjectId()).orElseThrow().getLeaderId())) {
                UserTask userTask = userTaskRepository.findByUserIdAndTaskId(userId, taskId).orElse(null);
                if(userTask != null) {
                    userTask.setId(userTask.getId());
                    userTask.setUserId(userId);
                    userTask.setTaskId(taskId);
                    userTaskRepository.save(userTask);
                } else {
                    UserTask newUserTask = new UserTask();
                    newUserTask.setUserId(userId);
                    newUserTask.setTaskId(taskId);
                    userTaskRepository.save(newUserTask);
                }
            }
            return convertToDTO(existingTaskOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO assignTaskByUsername(Long taskId, String username, Long assignerId) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        return assignTask(taskId, existingUserOptional.orElseThrow().getId(), assignerId);
    }

    @Override
    public TaskDTO unassignTask(Long taskId, Long userId, Long unAssignerId) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingTaskOptional.isPresent() && existingUserOptional.isPresent()) {
            if(unAssignerId.equals(projectRepository.findById(existingTaskOptional.get().getProjectId()).orElseThrow().getLeaderId())) {
                UserTask userTask = userTaskRepository.findByUserIdAndTaskId(userId, taskId).orElse(null);
                System.out.println("_________________________");
                System.out.println(taskId);
                System.out.println(userId);
                System.out.println(userTask.getId());
                System.out.println("_________________________");
                userTaskRepository.deleteById(userTask.getId());
            }
            return convertToDTO(existingTaskOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO unassignTaskByUsername(Long taskId, String username, Long unAssignerId) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        return unassignTask(taskId, existingUserOptional.orElseThrow().getId(), unAssignerId);
    }

    @Override
    public List<TaskDTO> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByName(String name, Pageable pageable) {
        return taskRepository.findByName(name, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByProjectId(Long projectId, Pageable pageable) {
        return taskRepository.findByProjectId(projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByExecutorId(Long executorId, Pageable pageable) {
        return taskRepository.findByExecutorId(executorId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable) {
        return taskRepository.findByProjectIdAndStatus(projectId, TaskStatus.valueOf(status), pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return taskRepository.count();
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setApiId(task.getApiId());
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
        taskDTO.setProgress(task.getProgress());
        taskDTO.setCreatedBy(task.getCreatedBy());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUpdatedAt(task.getUpdatedAt());
        taskDTO.setCanceledAt(task.getCanceledAt());
        taskDTO.setCanceledBy(task.getCanceledBy());
        taskDTO.setCanceledReason(task.getCanceledReason());
        taskDTO.setCanceledNote(task.getCanceledNote());

        return taskDTO;
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setApiId(taskDTO.getApiId());
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
        task.setProgress(taskDTO.getProgress());
        task.setCreatedBy(taskDTO.getCreatedBy());
        task.setCreatedAt(taskDTO.getCreatedAt());
        task.setUpdatedAt(taskDTO.getUpdatedAt());
        task.setCanceledAt(taskDTO.getCanceledAt());
        task.setCanceledBy(taskDTO.getCanceledBy());
        task.setCanceledReason(taskDTO.getCanceledReason());
        task.setCanceledNote(taskDTO.getCanceledNote());
        return task;
    }
}
