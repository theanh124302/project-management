package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.DefineRequestDTO;
import com.example.projectmanagement.service.DefineRequestService;
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
@RequestMapping("/api/v1/define-request")
public class DefineRequestController {

    @Autowired
    private DefineRequestService defineRequestService;

    @Value("${image.upload.dir}")
    private String uploadDir;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<DefineRequestDTO>> createDefineRequest(@RequestBody DefineRequestDTO defineRequestDTO) {
        DefineRequestDTO createdDefineRequest = defineRequestService.create(defineRequestDTO);
        if (createdDefineRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Define request created successfully")
                    .data(createdDefineRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create define request")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<DefineRequestDTO>> updateDefineRequest(@RequestBody DefineRequestDTO defineRequestDTO) {
        DefineRequestDTO updatedDefineRequest = defineRequestService.update(defineRequestDTO);
        if (updatedDefineRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Define request updated successfully")
                    .data(updatedDefineRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Define request not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<DefineRequestDTO>> deleteDefineRequest(@RequestParam Long id) {
        DefineRequestDTO deletedDefineRequest = defineRequestService.delete(id);
        if (deletedDefineRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Define request deleted successfully")
                    .data(deletedDefineRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Define request not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<DefineRequestDTO>> findDefineRequestById(@RequestParam Long id) {
        DefineRequestDTO defineRequest = defineRequestService.findById(id);
        if (defineRequest != null) {
            return ResponseEntity.ok(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Define request found")
                    .data(defineRequest)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DefineRequestDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Define request not found")
                    .build());
        }
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<DefineRequestDTO>>> findDefineRequestByProjectId(@RequestParam Long projectId, @RequestParam(defaultValue = "0") int page,
                                                                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<DefineRequestDTO>>builder()
                .status(HttpStatus.OK)
                .message("Define request found")
                .data(defineRequestService.findByProjectId(projectId, PageRequest.of(page, size)))
                .build());
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<ResponseTemplate<List<DefineRequestDTO>>> findDefineRequestByUserId(@RequestParam Long userId, @RequestParam(defaultValue = "0") int page,
                                                                                               @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<DefineRequestDTO>>builder()
                .status(HttpStatus.OK)
                .message("Define request found")
                .data(defineRequestService.findByUserId(userId, PageRequest.of(page, size)))
                .build());
    }

    @GetMapping("/findByTaskId")
    public ResponseEntity<ResponseTemplate<List<DefineRequestDTO>>> findDefineRequestByTaskId(@RequestParam Long taskId, @RequestParam(defaultValue = "0") int page,
                                                                                               @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<DefineRequestDTO>>builder()
                .status(HttpStatus.OK)
                .message("Define request found")
                .data(defineRequestService.findByTaskId(taskId, PageRequest.of(page, size)))
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
