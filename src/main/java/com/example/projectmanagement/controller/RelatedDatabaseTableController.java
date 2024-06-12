package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ApiImpactDTO;
import com.example.projectmanagement.dto.RelatedDatabaseTableDTO;
import com.example.projectmanagement.service.ApiImpactService;
import com.example.projectmanagement.service.RelatedDatabaseTableService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/relatedDatabaseTable")
public class RelatedDatabaseTableController {
    @Autowired
    private RelatedDatabaseTableService relatedDatabaseTableService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<RelatedDatabaseTableDTO>> createRelatedDatabaseTable(@RequestBody RelatedDatabaseTableDTO relatedDatabaseTableDTO) {
        RelatedDatabaseTableDTO createdRelatedDatabaseTable = relatedDatabaseTableService.create(relatedDatabaseTableDTO);
        if (createdRelatedDatabaseTable != null) {
            return ResponseEntity.ok(ResponseTemplate.<RelatedDatabaseTableDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("RelatedDatabaseTable created successfully")
                    .data(createdRelatedDatabaseTable)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<RelatedDatabaseTableDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create relatedDatabaseTable")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<RelatedDatabaseTableDTO>> updateRelatedDatabaseTable(@RequestBody RelatedDatabaseTableDTO relatedDatabaseTableDTO) {
        RelatedDatabaseTableDTO updatedRelatedDatabaseTable = relatedDatabaseTableService.update(relatedDatabaseTableDTO);
        if (updatedRelatedDatabaseTable != null) {
            return ResponseEntity.ok(ResponseTemplate.<RelatedDatabaseTableDTO>builder()
                    .status(HttpStatus.OK)
                    .message("RelatedDatabaseTable updated successfully")
                    .data(updatedRelatedDatabaseTable)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<RelatedDatabaseTableDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("RelatedDatabaseTable not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<RelatedDatabaseTableDTO>> deleteRelatedDatabaseTable(@RequestParam Long id) {
        relatedDatabaseTableService.delete(id);
        return ResponseEntity.ok(ResponseTemplate.<RelatedDatabaseTableDTO>builder()
                .status(HttpStatus.OK)
                .message("RelatedDatabaseTable deleted successfully")
                .build());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseTemplate<RelatedDatabaseTableDTO>> findRelatedDatabaseTableById(@PathVariable Long id) {
        RelatedDatabaseTableDTO relatedDatabaseTable = relatedDatabaseTableService.findById(id);
        if (relatedDatabaseTable != null) {
            return ResponseEntity.ok(ResponseTemplate.<RelatedDatabaseTableDTO>builder()
                    .status(HttpStatus.OK)
                    .message("RelatedDatabaseTable found successfully")
                    .data(relatedDatabaseTable)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<RelatedDatabaseTableDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("RelatedDatabaseTable not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<RelatedDatabaseTableDTO>>> findRelatedDatabaseTableByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
                                                                                                           @RequestParam(defaultValue = "10") int size) {
        List<RelatedDatabaseTableDTO> relatedDatabaseTables = relatedDatabaseTableService.findAllByApiId(apiId, PageRequest.of(page, size));
        if (relatedDatabaseTables != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<RelatedDatabaseTableDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("RelatedDatabaseTables found successfully")
                    .data(relatedDatabaseTables)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<RelatedDatabaseTableDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("RelatedDatabaseTables not found")
                    .build());
        }
    }
}