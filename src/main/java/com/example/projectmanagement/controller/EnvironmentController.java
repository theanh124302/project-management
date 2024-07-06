package com.example.projectmanagement.controller;


import com.example.projectmanagement.dto.EnvironmentDTO;
import com.example.projectmanagement.service.EnvironmentService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/environment")
public class EnvironmentController {
    @Autowired
    private EnvironmentService environmentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<EnvironmentDTO>> createEnvironment(@RequestBody EnvironmentDTO environmentDTO) {
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

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<EnvironmentDTO>> updateEnvironment(@RequestBody EnvironmentDTO environmentDTO) {
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

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<EnvironmentDTO>> deleteEnvironment(@RequestParam Long id) {
        EnvironmentDTO deletedEnvironment = environmentService.delete(id);
        if (deletedEnvironment != null) {
            return ResponseEntity.ok(ResponseTemplate.<EnvironmentDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Environment deleted successfully")
                    .data(deletedEnvironment)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<EnvironmentDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Environment not found")
                    .build());
        }
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<EnvironmentDTO>>> findEnvironmentByProjectId(@RequestParam Long projectId) {
        List<EnvironmentDTO> environments = environmentService.findByProjectId(projectId);
        if (environments != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<EnvironmentDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Environment found successfully")
                    .data(environments)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<EnvironmentDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Environment not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<EnvironmentDTO>> findEnvironmentById(@RequestParam Long id) {
        EnvironmentDTO environment = environmentService.findById(id);
        if (environment != null) {
            return ResponseEntity.ok(ResponseTemplate.<EnvironmentDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Environment found successfully")
                    .data(environment)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<EnvironmentDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Environment not found")
                    .build());
        }
    }

    @GetMapping("/countByProjectId")
    public ResponseEntity<ResponseTemplate<Long>> countByProjectId(@RequestParam Long projectId) {
        Long count = environmentService.countByProjectId(projectId);
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Environment count found successfully")
                .data(count)
                .build());
    }


}

