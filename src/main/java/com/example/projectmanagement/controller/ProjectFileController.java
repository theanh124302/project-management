package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.DocsDTO;
import com.example.projectmanagement.dto.ProjectFileDTO;
import com.example.projectmanagement.service.DocsService;
import com.example.projectmanagement.service.ProjectFileService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project-file")
public class ProjectFileController {

    @Autowired
    private ProjectFileService projectFileService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<ProjectFileDTO>> createProjectFile(@RequestParam MultipartFile file, @RequestParam String projectId) {
        ProjectFileDTO inputProjectFileDTO = new ProjectFileDTO();

        inputProjectFileDTO.setProjectId(Long.parseLong(projectId));
        ProjectFileDTO createdProjectFile = projectFileService.create(inputProjectFileDTO, file);
        if (createdProjectFile != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Project file created successfully")
                    .data(createdProjectFile)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create project file")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<ProjectFileDTO>> updateProjectFile(@RequestBody ProjectFileDTO projectFileDTO) {
        ProjectFileDTO updatedProjectFile = projectFileService.update(projectFileDTO);
        if (updatedProjectFile != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Project file updated successfully")
                    .data(updatedProjectFile)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Project file not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<ProjectFileDTO>> deleteProjectFile(@RequestParam Long id) {
        ProjectFileDTO deletedProjectFile = projectFileService.delete(id);
        if (deletedProjectFile != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Project file deleted successfully")
                    .data(deletedProjectFile)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Project file not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<ProjectFileDTO>> findProjectFileById(@RequestParam Long id) {
        ProjectFileDTO projectFile = projectFileService.findById(id);
        if (projectFile != null) {
            return ResponseEntity.ok(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Project file found successfully")
                    .data(projectFile)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ProjectFileDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Project file not found")
                    .build());
        }
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<ProjectFileDTO>>> findProjectFileByProjectId(@RequestParam Long projectId, @RequestParam(defaultValue = "0") int page,
                                                                                             @RequestParam(defaultValue = "10") int size) {
        List<ProjectFileDTO> projectFiles = projectFileService.findByProjectId(projectId, PageRequest.of(page, size));
        if (projectFiles != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<ProjectFileDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Project files found successfully")
                    .data(projectFiles)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<ProjectFileDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Project files not found")
                    .build());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<ResponseTemplate<Long>> countProjectFile() {
        Long count = projectFileService.count();
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Project files count")
                .data(count)
                .build());
    }

}