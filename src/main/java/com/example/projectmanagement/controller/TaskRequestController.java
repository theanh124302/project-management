package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.TaskRequestDTO;
import com.example.projectmanagement.service.TaskRequestService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task-request")
public class TaskRequestController {

    @Autowired
    private TaskRequestService taskRequestService;

    @Value("${image.upload.dir}")
    private String uploadDir;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<TaskRequestDTO>> createTaskRequest(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskRequestDTO createdTaskRequest = taskRequestService.create(taskRequestDTO);
        if (createdTaskRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Task request created successfully")
                    .data(createdTaskRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create task request")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<TaskRequestDTO>> updateTaskRequest(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskRequestDTO updatedTaskRequest = taskRequestService.update(taskRequestDTO);
        if (updatedTaskRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task request updated successfully")
                    .data(updatedTaskRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task request not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<TaskRequestDTO>> deleteTaskRequest(@RequestParam Long id) {
        TaskRequestDTO deletedTaskRequest = taskRequestService.delete(id);
        if (deletedTaskRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task request deleted successfully")
                    .data(deletedTaskRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task request not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<TaskRequestDTO>> findTaskRequestById(@RequestParam Long id) {
        TaskRequestDTO taskRequest = taskRequestService.findById(id);
        if (taskRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task request found")
                    .data(taskRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskRequestDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task request not found")
                    .build());
        }
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<TaskRequestDTO>>> findTaskRequestByProjectId(@RequestParam Long projectId, @RequestParam(defaultValue = "0") int page,
                                                                                               @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<TaskRequestDTO>>builder()
                .status(HttpStatus.OK)
                .message("Task request found")
                .data(taskRequestService.findByProjectId(projectId, PageRequest.of(page, size)))
                .build());
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<ResponseTemplate<List<TaskRequestDTO>>> findTaskRequestByUserId(@RequestParam Long userId, @RequestParam(defaultValue = "0") int page,
                                                                                            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<TaskRequestDTO>>builder()
                .status(HttpStatus.OK)
                .message("Task request found")
                .data(taskRequestService.findByUserId(userId, PageRequest.of(page, size)))
                .build());
    }

    @GetMapping("/findByTaskId")
    public ResponseEntity<ResponseTemplate<List<TaskRequestDTO>>> findTaskRequestByTaskId(@RequestParam Long taskId, @RequestParam(defaultValue = "0") int page,
                                                                                            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<TaskRequestDTO>>builder()
                .status(HttpStatus.OK)
                .message("Task request found")
                .data(taskRequestService.findByTaskId(taskId, PageRequest.of(page, size)))
                .build());
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<ResponseTemplate<String>> uploadImage(@RequestPart("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            Files.write(filePath, file.getBytes());
            String fileUrl = "/images/" + filename;
            return ResponseEntity.ok(ResponseTemplate.<String>builder()
                    .status(HttpStatus.OK)
                    .message("Image uploaded successfully")
                    .data(fileUrl)
                    .build());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<String>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to upload image")
                    .build());
        }
    }

}
