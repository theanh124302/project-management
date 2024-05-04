package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.WorkspaceDTO;
import com.example.projectmanagement.service.WorkspaceService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/workspace")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<WorkspaceDTO>> createWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
        WorkspaceDTO createdWorkspace = workspaceService.create(workspaceDTO);
        if (createdWorkspace != null) {
            return ResponseEntity.ok(ResponseTemplate.<WorkspaceDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Workspace created successfully")
                    .data(createdWorkspace)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<WorkspaceDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create workspace")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<WorkspaceDTO>> updateWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
        WorkspaceDTO updatedWorkspace = workspaceService.update(workspaceDTO);
        if (updatedWorkspace != null) {
            return ResponseEntity.ok(ResponseTemplate.<WorkspaceDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Workspace updated successfully")
                    .data(updatedWorkspace)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<WorkspaceDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Workspace not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<WorkspaceDTO>> deleteWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
        WorkspaceDTO deletedWorkspace = workspaceService.delete(workspaceDTO);
        if (deletedWorkspace != null) {
            return ResponseEntity.ok(ResponseTemplate.<WorkspaceDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Workspace deleted successfully")
                    .data(deletedWorkspace)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<WorkspaceDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Workspace not found")
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseTemplate<List<WorkspaceDTO>>> getAllWorkspaces(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<WorkspaceDTO> workspaces = workspaceService.getAllWorkspaces(pageable);

        long totalItems = workspaceService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return ResponseEntity.ok(ResponseTemplate.<List<WorkspaceDTO>>builder()
                .status(HttpStatus.OK)
                .message("Workspaces found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(workspaces)
                .build());
    }

    @GetMapping("/findByName")
    public ResponseEntity<ResponseTemplate<List<WorkspaceDTO>>> findWorkspacesByName(@RequestParam String name,
                                                                                     @RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<WorkspaceDTO> workspaces = workspaceService.findByName(name, pageable);
        long totalItems = workspaceService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<WorkspaceDTO>>builder()
                .status(HttpStatus.OK)
                .message("Workspaces found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(workspaces)
                .build());
    }

    @GetMapping("/findByCreatorId")
    public ResponseEntity<ResponseTemplate<List<WorkspaceDTO>>> findWorkspacesByLeaderId(@RequestParam Long creatorId,
                                                                                         @RequestParam(defaultValue = "0") int page,
                                                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<WorkspaceDTO> workspaces = workspaceService.findByCreatorId(creatorId, pageable);
        long totalItems = workspaceService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<WorkspaceDTO>>builder()
                .status(HttpStatus.OK)
                .message("Workspaces found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(workspaces)
                .build());
    }
}