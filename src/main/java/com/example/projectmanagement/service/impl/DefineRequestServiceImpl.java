package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DefineRequestDTO;
import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.entity.Comment;
import com.example.projectmanagement.entity.DefineRequest;
import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.repository.DefineRequestRepository;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.TaskRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.DefineRequestService;
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
public class DefineRequestServiceImpl implements DefineRequestService {

    @Autowired
    private DefineRequestRepository defineRequestRepository;

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
    public DefineRequestDTO create(DefineRequestDTO defineRequestDTO) {
        DefineRequest defineRequest = convertToEntity(defineRequestDTO);
        defineRequest.setCreatedAt(Timestamp.from(Instant.now()));
        UserDTO leader = userRepository.findById(projectRepository.findById(defineRequestDTO.getProjectId()).get().getLeaderId()).map(UserDTO::new).orElse(null);
        if (leader != null) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(leader.getEmail());
            simpleMailMessage.setSubject("Define Request Created");
            Optional<Task> existingTaskOptional = taskRepository.findById(defineRequestDTO.getTaskId());
            String taskName = existingTaskOptional.orElseThrow().getName();
            String projectName = projectRepository.findById(existingTaskOptional.orElseThrow().getProjectId()).orElseThrow().getName();
            String submitterName = userRepository.findById(defineRequestDTO.getUserId()).orElseThrow().getUsername();
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
        return convertToDTO(defineRequestRepository.save(defineRequest));
    }

    @Override
    public DefineRequestDTO update(DefineRequestDTO defineRequestDTO) {
        Optional<DefineRequest> existingDefineRequestOptional = defineRequestRepository.findById(defineRequestDTO.getId());
        if (existingDefineRequestOptional.isPresent()) {
            DefineRequest existingDefineRequest = existingDefineRequestOptional.get();
            existingDefineRequest.setProjectId(defineRequestDTO.getProjectId());
            existingDefineRequest.setUrl(defineRequestDTO.getUrl());
            existingDefineRequest.setUserId(defineRequestDTO.getUserId());
            existingDefineRequest.setTaskId(defineRequestDTO.getTaskId());
            existingDefineRequest.setDescription(defineRequestDTO.getDescription());
            existingDefineRequest.setContent(defineRequestDTO.getContent());
            return convertToDTO(defineRequestRepository.save(existingDefineRequest));
        } else {
            return null; // Handle the case where the define request doesn't exist
        }
    }

    @Override
    public DefineRequestDTO delete(Long id) {
        Optional<DefineRequest> existingDefineRequestOptional = defineRequestRepository.findById(id);
        if (existingDefineRequestOptional.isPresent()) {
            DefineRequest existingDefineRequest = existingDefineRequestOptional.get();
            defineRequestRepository.delete(existingDefineRequest);
            return convertToDTO(existingDefineRequest);
        } else {
            return null; // Handle the case where the define request doesn't exist
        }
    }

    @Override
    public DefineRequestDTO findById(Long id) {
        Optional<DefineRequest> defineRequestOptional = defineRequestRepository.findById(id);
        return defineRequestOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DefineRequestDTO> findByProjectId(Long projectId, Pageable pageable) {
        Page<DefineRequest> defineRequestPage = defineRequestRepository.findByProjectId(projectId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<DefineRequestDTO> findByUserId(Long userId, Pageable pageable) {
        Page<DefineRequest> defineRequestPage = defineRequestRepository.findByUserId(userId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<DefineRequestDTO> findByTaskId(Long taskId, Pageable pageable) {
        Page<DefineRequest> defineRequestPage = defineRequestRepository.findByTaskId(taskId, pageable);
        return defineRequestPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return defineRequestRepository.count();
    }

    private DefineRequestDTO convertToDTO(DefineRequest defineRequest) {
        DefineRequestDTO defineRequestDTO = new DefineRequestDTO();
        defineRequestDTO.setId(defineRequest.getId());
        defineRequestDTO.setUrl(defineRequest.getUrl());
        defineRequestDTO.setProjectId(defineRequest.getProjectId());
        defineRequestDTO.setUserId(defineRequest.getUserId());
        defineRequestDTO.setTaskId(defineRequest.getTaskId());
        defineRequestDTO.setDescription(defineRequest.getDescription());
        defineRequestDTO.setContent(defineRequest.getContent());
        return defineRequestDTO;
    }

    private DefineRequest convertToEntity(DefineRequestDTO defineRequestDTO) {
        DefineRequest defineRequest = new DefineRequest();
        defineRequest.setId(defineRequestDTO.getId());
        defineRequest.setUrl(defineRequestDTO.getUrl());
        defineRequest.setProjectId(defineRequestDTO.getProjectId());
        defineRequest.setUserId(defineRequestDTO.getUserId());
        defineRequest.setTaskId(defineRequestDTO.getTaskId());
        defineRequest.setDescription(defineRequestDTO.getDescription());
        defineRequest.setContent(defineRequestDTO.getContent());
        return defineRequest;
    }
}
