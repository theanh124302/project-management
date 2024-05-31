package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.FolderDTO;
import com.example.projectmanagement.service.FolderService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/folder")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<FolderDTO>> createFolder(@RequestBody FolderDTO folderDTO) {
        FolderDTO createdFolder = folderService.create(folderDTO);
        if (createdFolder != null) {
            return ResponseEntity.ok(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Folder created successfully")
                    .data(createdFolder)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create folder")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<FolderDTO>> updateFolder(@RequestBody FolderDTO folderDTO) {
        FolderDTO updatedFolder = folderService.update(folderDTO);
        if (updatedFolder != null) {
            return ResponseEntity.ok(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Folder updated successfully")
                    .data(updatedFolder)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Folder not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<FolderDTO>> deleteFolder(@RequestParam Long id) {
        FolderDTO deletedFolder = folderService.delete(id);
        if (deletedFolder != null) {
            return ResponseEntity.ok(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Folder deleted successfully")
                    .data(deletedFolder)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Folder not found")
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseTemplate<List<FolderDTO>>> getAllFolders(@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<FolderDTO> folders = folderService.getAllFolders(pageable);

        long totalItems = folderService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return ResponseEntity.ok(ResponseTemplate.<List<FolderDTO>>builder()
                .status(HttpStatus.OK)
                .message("Folders found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(folders)
                .build());
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<FolderDTO>> findFolderById(@RequestParam Long id) {
        FolderDTO folder = folderService.findById(id);
        if (folder != null) {
            return ResponseEntity.ok(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Folder found successfully")
                    .data(folder)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<FolderDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Folder not found")
                    .build());
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<ResponseTemplate<List<FolderDTO>>> findFoldersByName(@RequestParam String name,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<FolderDTO> folders = folderService.findByName(name, pageable);
        long totalItems = folderService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<FolderDTO>>builder()
                .status(HttpStatus.OK)
                .message("Folders found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(folders)
                .build());
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<FolderDTO>>> findFoldersByProjectId(@RequestParam Long projectId,
                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<FolderDTO> folders = folderService.findByProjectId(projectId, pageable);
        long totalItems = folderService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<FolderDTO>>builder()
                .status(HttpStatus.OK)
                .message("Folders found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(folders)
                .build());
    }
}
