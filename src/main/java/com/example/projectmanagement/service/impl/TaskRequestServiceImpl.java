package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.TaskRequestDTO;
import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.entity.TaskRequest;
import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.repository.TaskRequestRepository;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.TaskRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.TaskRequestService;
import com.example.projectmanagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskRequestServiceImpl implements TaskRequestService {

    @Autowired
    private TaskRequestRepository taskRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;

    @Override
    public TaskRequestDTO create(TaskRequestDTO taskRequestDTO) {
        TaskRequest taskRequest = convertToEntity(taskRequestDTO);
        taskRequest.setCreatedAt(Timestamp.from(Instant.now()));
        UserDTO leader = userRepository.findById(projectRepository.findById(taskRequestDTO.getProjectId()).get().getLeaderId()).map(UserDTO::new).orElse(null);
        if (leader != null) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(leader.getEmail());
            simpleMailMessage.setSubject("Define Request Created");
            Optional<Task> existingTaskOptional = taskRepository.findById(taskRequestDTO.getTaskId());
            String taskName = existingTaskOptional.orElseThrow().getName();
            String projectName = projectRepository.findById(existingTaskOptional.orElseThrow().getProjectId()).orElseThrow().getName();
            String submitterName = userRepository.findById(taskRequestDTO.getUserId()).orElseThrow().getUsername();
            LocalDateTime submissionDate = LocalDateTime.now();
            String emailText = String.format(
                    "Dear Project Manager,\n\n" +
                            "A new task completion request has been submitted for your review. Please find the details below:\n\n" +
                            "Task: %s\n" +
                            "Project: %s\n" +
                            "Submitted by: %s\n" +
                            "Submission Date: %s\n\n" +
                            "Please review and confirm the completion of this task.\n\n" +
                            "Best regards,\n" +
                            "Project Management Team",
                    taskName, projectName, submitterName, submissionDate
            );
            simpleMailMessage.setText(emailText);

            emailService.sendEmail(simpleMailMessage);
        }
        return convertToDTO(taskRequestRepository.save(taskRequest));
    }

    @Override
    public TaskRequestDTO update(TaskRequestDTO taskRequestDTO) {
        Optional<TaskRequest> existingDefineRequestOptional = taskRequestRepository.findById(taskRequestDTO.getId());
        if (existingDefineRequestOptional.isPresent()) {
            TaskRequest existingTaskRequest = existingDefineRequestOptional.get();
            existingTaskRequest.setProjectId(taskRequestDTO.getProjectId());
            existingTaskRequest.setUrl(taskRequestDTO.getUrl());
            existingTaskRequest.setUserId(taskRequestDTO.getUserId());
            existingTaskRequest.setTaskId(taskRequestDTO.getTaskId());
            existingTaskRequest.setDescription(taskRequestDTO.getDescription());
            existingTaskRequest.setContent(taskRequestDTO.getContent());
            return convertToDTO(taskRequestRepository.save(existingTaskRequest));
        } else {
            return null; // Handle the case where the define request doesn't exist
        }
    }

    @Override
    public TaskRequestDTO delete(Long id) {
        Optional<TaskRequest> existingDefineRequestOptional = taskRequestRepository.findById(id);
        if (existingDefineRequestOptional.isPresent()) {
            TaskRequest existingTaskRequest = existingDefineRequestOptional.get();
            taskRequestRepository.delete(existingTaskRequest);
            return convertToDTO(existingTaskRequest);
        } else {
            return null; // Handle the case where the define request doesn't exist
        }
    }

    @Override
    public TaskRequestDTO findById(Long id) {
        Optional<TaskRequest> defineRequestOptional = taskRequestRepository.findById(id);
        return defineRequestOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<TaskRequestDTO> findByProjectId(Long projectId, Pageable pageable) {
        Page<TaskRequest> defineRequestPage = taskRequestRepository.findByProjectId(projectId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskRequestDTO> findByUserId(Long userId, Pageable pageable) {
        Page<TaskRequest> defineRequestPage = taskRequestRepository.findByUserId(userId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskRequestDTO> findByTaskId(Long taskId, Pageable pageable) {
        Page<TaskRequest> defineRequestPage = taskRequestRepository.findByTaskId(taskId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return taskRequestRepository.count();
    }

    private TaskRequestDTO convertToDTO(TaskRequest taskRequest) {
        TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
        taskRequestDTO.setId(taskRequest.getId());
        taskRequestDTO.setUrl(taskRequest.getUrl());
        taskRequestDTO.setProjectId(taskRequest.getProjectId());
        taskRequestDTO.setUserId(taskRequest.getUserId());
        taskRequestDTO.setTaskId(taskRequest.getTaskId());
        taskRequestDTO.setDescription(taskRequest.getDescription());
        taskRequestDTO.setContent(taskRequest.getContent());
        taskRequestDTO.setCreatedAt(taskRequest.getCreatedAt());
        return taskRequestDTO;
    }

    private TaskRequest convertToEntity(TaskRequestDTO taskRequestDTO) {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setId(taskRequestDTO.getId());
        taskRequest.setUrl(taskRequestDTO.getUrl());
        taskRequest.setProjectId(taskRequestDTO.getProjectId());
        taskRequest.setUserId(taskRequestDTO.getUserId());
        taskRequest.setTaskId(taskRequestDTO.getTaskId());
        taskRequest.setDescription(taskRequestDTO.getDescription());
        taskRequest.setContent(taskRequestDTO.getContent());
        taskRequest.setCreatedAt(taskRequestDTO.getCreatedAt());
        return taskRequest;
    }
}
