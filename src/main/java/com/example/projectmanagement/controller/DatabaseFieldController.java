package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.DatabaseFieldDTO;
import com.example.projectmanagement.service.DatabaseFieldService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database-field")
public class DatabaseFieldController {

    @Autowired
    private DatabaseFieldService databaseFieldService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<DatabaseFieldDTO>> createDatabaseField(@RequestBody DatabaseFieldDTO databaseFieldDTO) {
        DatabaseFieldDTO createdDatabaseField = databaseFieldService.create(databaseFieldDTO);
        if (createdDatabaseField != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Database field created successfully")
                    .data(createdDatabaseField)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create database field")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<DatabaseFieldDTO>> updateDatabaseField(@RequestBody DatabaseFieldDTO databaseFieldDTO) {
        DatabaseFieldDTO updatedDatabaseField = databaseFieldService.update(databaseFieldDTO);
        if (updatedDatabaseField != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database field updated successfully")
                    .data(updatedDatabaseField)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database field not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<DatabaseFieldDTO>> deleteDatabaseField(@RequestParam Long id) {
        DatabaseFieldDTO deletedDatabaseField = databaseFieldService.delete(id);
        if (deletedDatabaseField != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database field deleted successfully")
                    .data(deletedDatabaseField)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database field not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<DatabaseFieldDTO>> findById(@RequestParam Long id) {
        DatabaseFieldDTO databaseFieldDTO = databaseFieldService.findById(id);
        if (databaseFieldDTO != null) {
            return ResponseEntity.ok(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Database field found")
                    .data(databaseFieldDTO)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DatabaseFieldDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database field not found")
                    .build());
        }
    }

    @GetMapping("/findByDatabaseTableId")
    public ResponseEntity<ResponseTemplate<List<DatabaseFieldDTO>>> findByDatabaseTableId(@RequestParam Long databaseTableId, @RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<DatabaseFieldDTO> databaseFieldDTOList = databaseFieldService.findByDatabaseTableId(databaseTableId, pageable);
        return ResponseEntity.ok(ResponseTemplate.<List<DatabaseFieldDTO>>builder()
                .status(HttpStatus.OK)
                .message("Database fields found")
                .data(databaseFieldDTOList)
                .build());
    }

}
