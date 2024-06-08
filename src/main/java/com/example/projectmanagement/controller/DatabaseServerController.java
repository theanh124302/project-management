package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.DatabaseServerDTO;
import com.example.projectmanagement.service.DatabaseServerService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database-server")
public class DatabaseServerController {

    @Autowired
    private DatabaseServerService databaseServerService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<DatabaseServerDTO>> createDatabaseServer(@RequestBody DatabaseServerDTO databaseServerDTO) {
        DatabaseServerDTO createdDatabaseServer = databaseServerService.create(databaseServerDTO);
        if (createdDatabaseServer != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Database server created successfully")
                    .data(createdDatabaseServer)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create database server")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<DatabaseServerDTO>> updateDatabaseServer(@RequestBody DatabaseServerDTO databaseServerDTO) {
        DatabaseServerDTO updatedDatabaseServer = databaseServerService.update(databaseServerDTO);
        if (updatedDatabaseServer != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database server updated successfully")
                    .data(updatedDatabaseServer)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database server not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<DatabaseServerDTO>> deleteDatabaseServer(@RequestParam Long id) {
        DatabaseServerDTO deletedDatabaseServer = databaseServerService.delete(id);
        if (deletedDatabaseServer != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database server deleted successfully")
                    .data(deletedDatabaseServer)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database server not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<DatabaseServerDTO>> findDatabaseServerById(@RequestParam Long id) {
        DatabaseServerDTO foundDatabaseServer = databaseServerService.findById(id);
        if (foundDatabaseServer != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database server found successfully")
                    .data(foundDatabaseServer)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseServerDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database server not found")
                    .build());
        }
    }

    @GetMapping("/findAllByProjectId")
    public ResponseEntity<ResponseTemplate<List<DatabaseServerDTO>>> findAllDatabaseServersByProjectId(@RequestParam Long projectId, @RequestParam(defaultValue = "0") int page,
                                                                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<DatabaseServerDTO> foundDatabaseServers = databaseServerService.findAllByProjectId(projectId, pageable);
        if (foundDatabaseServers != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<DatabaseServerDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Database servers found successfully")
                    .data(foundDatabaseServers)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<DatabaseServerDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database servers not found")
                    .build());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<ResponseTemplate<Long>> countDatabaseServers() {
        Long count = databaseServerService.count();
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Database servers count found successfully")
                .data(count)
                .build());
    }

}
