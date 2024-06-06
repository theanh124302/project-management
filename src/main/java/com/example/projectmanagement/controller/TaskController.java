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

    @PostMapping("/assign")
    public ResponseEntity<ResponseTemplate<TaskDTO>> assignTask(@RequestParam Long taskId, @RequestParam Long userId, @RequestParam Long assignerId) {
        TaskDTO assignedTask = taskService.assignTask(taskId, userId, assignerId);
        if (assignedTask != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task assigned successfully")
                    .data(assignedTask)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @PostMapping("/assignByUsername")
    public ResponseEntity<ResponseTemplate<TaskDTO>> assignTaskByUsername(@RequestParam Long taskId, @RequestParam String username, @RequestParam Long assignerId) {
        TaskDTO assignedTask = taskService.assignTaskByUsername(taskId, username, assignerId);
        if (assignedTask != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task assigned successfully")
                    .data(assignedTask)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @PostMapping("/unassign")
    public ResponseEntity<ResponseTemplate<TaskDTO>> unassignTask(@RequestParam Long taskId, @RequestParam Long userId, @RequestParam Long unAssignerId) {
        TaskDTO unassignedTask = taskService.unassignTask(taskId, userId, unAssignerId);
        if (unassignedTask != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task unassigned successfully")
                    .data(unassignedTask)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @PostMapping("/unassignByUsername")
    public ResponseEntity<ResponseTemplate<TaskDTO>> unassignTaskByUsername(@RequestParam Long taskId, @RequestParam String username, @RequestParam Long unAssignerId) {
        TaskDTO unassignedTask = taskService.unassignTaskByUsername(taskId, username, unAssignerId);
        if (unassignedTask != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Task unassigned successfully")
                    .data(unassignedTask)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @PostMapping("/addReviewer")
    public ResponseEntity<ResponseTemplate<TaskDTO>> addReviewer(@RequestParam Long taskId, @RequestParam Long userId, @RequestParam Long adderId) {
        TaskDTO task = taskService.addReviewer(taskId, userId, adderId);
        if (task != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Reviewer added successfully")
                    .data(task)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @PostMapping("/addReviewerByUsername")
    public ResponseEntity<ResponseTemplate<TaskDTO>> addReviewerByUsername(@RequestParam Long taskId, @RequestParam String username, @RequestParam Long adderId) {
        TaskDTO task = taskService.addReviewerByUsername(taskId, username, adderId);
        if (task != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Reviewer added successfully")
                    .data(task)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @PostMapping("/removeReviewer")
    public ResponseEntity<ResponseTemplate<TaskDTO>> removeReviewer(@RequestParam Long taskId, @RequestParam Long removerId) {
        TaskDTO task = taskService.removeReviewer(taskId, removerId);
        if (task != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Reviewer removed successfully")
                    .data(task)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }

    @PostMapping("/removeReviewerByUsername")
    public ResponseEntity<ResponseTemplate<TaskDTO>> removeReviewerByUsername(@RequestParam Long taskId, @RequestParam String username) {
        TaskDTO task = taskService.removeReviewerByUsername(taskId, username);
        if (task != null) {
            return ResponseEntity.ok(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Reviewer removed successfully")
                    .data(task)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TaskDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Task not found")
                    .build());
        }
    }


}
