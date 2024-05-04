package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ProjectDTO;
import com.example.projectmanagement.entity.Project;
import com.example.projectmanagement.service.ProjectService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<ProjectDTO>> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.create(projectDTO);
        if (createdProject != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Project created successfully")
                    .data(createdProject)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create project")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<ProjectDTO>> updateProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.update(projectDTO);
        if (updatedProject != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Project updated successfully")
                    .data(updatedProject)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Project not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<ProjectDTO>> deleteProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO deletedProject = projectService.delete(projectDTO);
        if (deletedProject != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Project deleted successfully")
                    .data(deletedProject)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Project not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<ProjectDTO>> findProjectById(@RequestParam Long id) {
        ProjectDTO project = projectService.findById(id);
        if (project != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Project found successfully")
                    .data(project)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ProjectDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Project not found")
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseTemplate<List<ProjectDTO>>> getAllProjects(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProjectDTO> projects = projectService.getAllProjects(pageable);

        long totalItems = projectService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return ResponseEntity.ok(ResponseTemplate.<List<ProjectDTO>>builder()
                .status(HttpStatus.OK)
                .message("Projects found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(projects)
                .build());
    }

    @GetMapping("/findByName")
    public ResponseEntity<ResponseTemplate<List<ProjectDTO>>> findProjectsByName(@RequestParam String name,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProjectDTO> projects = projectService.findByName(name, pageable);
        long totalItems = projectService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<ProjectDTO>>builder()
                .status(HttpStatus.OK)
                .message("Projects found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(projects)
                .build());
    }

    @GetMapping("/findByLeaderId")
    public ResponseEntity<ResponseTemplate<List<ProjectDTO>>> findProjectsByLeaderId(@RequestParam Long leaderId,
                                                                                     @RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProjectDTO> projects = projectService.findByLeaderId(leaderId, pageable);
        long totalItems = projectService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<ProjectDTO>>builder()
                .status(HttpStatus.OK)
                .message("Projects found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(projects)
                .build());
    }

    @GetMapping("/findByWorkspaceId")
    public ResponseEntity<ResponseTemplate<List<ProjectDTO>>> findProjectsByWorkspaceId(@RequestParam Long workspaceId,
                                                                                     @RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProjectDTO> projects = projectService.findByWorkspaceId(workspaceId, pageable);
        long totalItems = projectService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<ProjectDTO>>builder()
                .status(HttpStatus.OK)
                .message("Projects found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(projects)
                .build());
    }
}

