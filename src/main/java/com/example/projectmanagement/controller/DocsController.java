package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.DocsDTO;
import com.example.projectmanagement.service.DocsService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/docs")
public class DocsController {
    @Autowired
    private DocsService docsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<DocsDTO>> createDocs(@RequestBody DocsDTO docsDTO) {
        DocsDTO createdDocs = docsService.create(docsDTO);
        if (createdDocs != null) {
            return ResponseEntity.ok(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Docs created successfully")
                    .data(createdDocs)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create docs")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<DocsDTO>> updateDocs(@RequestBody DocsDTO docsDTO) {
        DocsDTO updatedDocs = docsService.update(docsDTO);
        if (updatedDocs != null) {
            return ResponseEntity.ok(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Docs updated successfully")
                    .data(updatedDocs)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Docs not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<DocsDTO>> deleteDocs(@RequestParam Long id, @RequestParam Long deletedBy) {
        DocsDTO deletedDocs = docsService.delete(id, deletedBy);
        if (deletedDocs != null) {
            return ResponseEntity.ok(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Docs deleted successfully")
                    .data(deletedDocs)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Docs not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<DocsDTO>> findDocsById(@RequestParam Long id) {
        DocsDTO docs = docsService.findById(id);
        if (docs != null) {
            return ResponseEntity.ok(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Docs found successfully")
                    .data(docs)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DocsDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Docs not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<DocsDTO>>> findDocsByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        List<DocsDTO> docs = docsService.findByApiId(apiId, PageRequest.of(page, size));
        if (docs != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<DocsDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Docs found successfully")
                    .data(docs)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<DocsDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Docs not found")
                    .build());
        }
    }
}
