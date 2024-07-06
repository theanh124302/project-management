package com.example.projectmanagement.controller;


import com.example.projectmanagement.dto.DatabaseTableDTO;
import com.example.projectmanagement.service.DatabaseTableService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database-table")
public class DatabaseTableController {

    @Autowired
    private DatabaseTableService databaseTableService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<DatabaseTableDTO>> createDatabaseTable(@RequestBody DatabaseTableDTO databaseTableDTO) {
        DatabaseTableDTO createdDatabaseTable = databaseTableService.create(databaseTableDTO);
        if (createdDatabaseTable != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Database table created successfully")
                    .data(createdDatabaseTable)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create database table")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<DatabaseTableDTO>> updateDatabaseTable(@RequestBody DatabaseTableDTO databaseTableDTO) {
        DatabaseTableDTO updatedDatabaseTable = databaseTableService.update(databaseTableDTO);
        if (updatedDatabaseTable != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database table updated successfully")
                    .data(updatedDatabaseTable)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database table not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<DatabaseTableDTO>> deleteDatabaseTable(@RequestParam Long id) {
        DatabaseTableDTO deletedDatabaseTable = databaseTableService.delete(id);
        if (deletedDatabaseTable != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database table deleted successfully")
                    .data(deletedDatabaseTable)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database table not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<DatabaseTableDTO>> findById(@RequestParam Long id) {
        DatabaseTableDTO databaseTable = databaseTableService.findById(id);
        if (databaseTable != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database table found")
                    .data(databaseTable)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseTableDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database table not found")
                    .build());
        }
    }

    @GetMapping("/findAllByDatabaseServerId")
    public ResponseEntity<ResponseTemplate<List<DatabaseTableDTO>>> findAllByDatabaseServerId(@RequestParam Long databaseServerId, @RequestParam(defaultValue = "0") int page,
                                                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<DatabaseTableDTO> databaseTables = databaseTableService.findAllByDatabaseServerId(databaseServerId, pageable);
        return ResponseEntity.ok(ResponseTemplate.<List<DatabaseTableDTO>>builder()
                .status(HttpStatus.OK)
                .message("Database tables found")
                .data(databaseTables)
                .build());
    }

    @GetMapping("/countByProjectId")
    public ResponseEntity<ResponseTemplate<Long>> countByProjectId(@RequestParam Long projectId) {
        Long count = databaseTableService.countByProjectId(projectId);
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Database tables count")
                .data(count)
                .build());
    }

}
