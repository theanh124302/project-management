package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.EnvironmentDTO;
import com.example.projectmanagement.dto.ProjectDTO;
import com.example.projectmanagement.service.EnvironmentService;
import com.example.projectmanagement.service.ProjectService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/environment")
public class EnvironmentController {

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<EnvironmentDTO>> createEnvironment(@RequestBody EnvironmentDTO environmentDTO) {
        ProjectDTO project = projectService.findById(environmentDTO.getProjectId());
        if (!Objects.equals(project.getLeaderId(), environmentDTO.getCreatedBy())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseTemplate.<EnvironmentDTO>builder()
                    .status(HttpStatus.FORBIDDEN)
                    .message("You are not authorized to create environment for this project")
                    .build());
        }else {
            EnvironmentDTO createdEnvironment = environmentService.create(environmentDTO);
            if (createdEnvironment != null) {
                return ResponseEntity.ok(ResponseTemplate.<EnvironmentDTO>builder()
                        .status(HttpStatus.CREATED)
                        .message("Environment created successfully")
                        .data(createdEnvironment)
                        .build());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<EnvironmentDTO>builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Failed to create environment")
                        .build());
            }
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<EnvironmentDTO>> updateEnvironment(@RequestBody EnvironmentDTO environmentDTO) {
        ProjectDTO project = projectService.findById(environmentDTO.getProjectId());
        if (!Objects.equals(project.getLeaderId(), environmentDTO.getCreatedBy())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseTemplate.<EnvironmentDTO>builder()
                    .status(HttpStatus.FORBIDDEN)
                    .message("You are not authorized to update environment for this project")
                    .build());
        } else {
            EnvironmentDTO updatedEnvironment = environmentService.update(environmentDTO);
            if (updatedEnvironment != null) {
                return ResponseEntity.ok(ResponseTemplate.<EnvironmentDTO>builder()
                        .status(HttpStatus.OK)
                        .message("Environment updated successfully")
                        .data(updatedEnvironment)
                        .build());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<EnvironmentDTO>builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Environment not found")
                        .build());
            }
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<EnvironmentDTO>> deleteEnvironment(@RequestParam Long id, @RequestParam Long userId) {
        EnvironmentDTO environment = environmentService.findById(id);
        if (environment != null) {
            if (!Objects.equals(environment.getCreatedBy(), userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseTemplate.<EnvironmentDTO>builder()
                        .status(HttpStatus.FORBIDDEN)
                        .message("You are not authorized to delete this environment")
                        .build());
            } else {
                EnvironmentDTO deletedEnvironment = environmentService.delete(id);
                return ResponseEntity.ok(ResponseTemplate.<EnvironmentDTO>builder()
                        .status(HttpStatus.OK)
                        .message("Environment deleted successfully")
                        .data(deletedEnvironment)
                        .build());
            }
        }
        return null;
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<EnvironmentDTO>>> findEnvironmentsByProjectId(@RequestParam Long projectId,
                                                                                              @RequestParam(defaultValue = "0") int page,
                                                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EnvironmentDTO> environments = environmentService.findByProjectId(projectId, pageable);
        long totalItems = environmentService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<EnvironmentDTO>>builder()
                .status(HttpStatus.OK)
                .message("Environments found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(environments)
                .build());
    }

}
