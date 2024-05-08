package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.TaskDTO;
import com.example.projectmanagement.service.TaskService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<TaskDTO>> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.create(taskDTO);
        if (createdTask != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Task created successfully")
                    .data(createdTask)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create task")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<TaskDTO>> updateTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = taskService.update(taskDTO);
        if (updatedTask != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task updated successfully")
                    .data(updatedTask)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<TaskDTO>> deleteTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO deletedTask = taskService.delete(taskDTO);
        if (deletedTask != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task deleted successfully")
                    .data(deletedTask)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<TaskDTO>> findTaskById(@RequestParam Long id) {
        TaskDTO task = taskService.findById(id);
        if (task != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task found successfully")
                    .data(task)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseTemplate<List<TaskDTO>>> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TaskDTO> tasks = taskService.getAllTasks(pageable);
        long totalItems = taskService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<TaskDTO>>builder()
                .status(HttpStatus.OK)
                .message("Tasks found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(tasks)
                .build());
    }

    @GetMapping("/findByName")
    public ResponseEntity<ResponseTemplate<List<TaskDTO>>> findTasksByName(@RequestParam String name,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TaskDTO> tasks = taskService.findByName(name, pageable);
        long totalItems = taskService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<TaskDTO>>builder()
                .status(HttpStatus.OK)
                .message("Tasks found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(tasks)
                .build());
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<TaskDTO>>> findTasksByProjectId(@RequestParam Long projectId,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TaskDTO> tasks = taskService.findByProjectId(projectId, pageable);
        long totalItems = taskService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<TaskDTO>>builder()
                .status(HttpStatus.OK)
                .message("Tasks found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(tasks)
                .build());
    }

    @GetMapping("/findByExecutorId")
    public ResponseEntity<ResponseTemplate<List<TaskDTO>>> findTasksByExecutorId(@RequestParam Long executorId,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TaskDTO> tasks = taskService.findByExecutorId(executorId, pageable);
        long totalItems = taskService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<TaskDTO>>builder()
                .status(HttpStatus.OK)
                .message("Tasks found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(tasks)
                .build());
    }

    @GetMapping("/findByProjectIdAndStatus")
    public ResponseEntity<ResponseTemplate<List<TaskDTO>>> findTasksByProjectIdAndStatus(@RequestParam Long projectId,
                                                                                         @RequestParam String status,
                                                                                         @RequestParam(defaultValue = "0") int page,
                                                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TaskDTO> tasks = taskService.findByProjectIdAndStatus(projectId, status, pageable);
        long totalItems = taskService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<TaskDTO>>builder()
                .status(HttpStatus.OK)
                .message("Tasks found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(tasks)
                .build());
    }
}
