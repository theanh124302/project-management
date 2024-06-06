package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.HeaderDTO;
import com.example.projectmanagement.service.HeaderService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/header")
public class HeaderController {

    @Autowired
    private HeaderService headerService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<HeaderDTO>> createHeader(@RequestBody HeaderDTO headerDTO) {
        HeaderDTO createdHeader = headerService.create(headerDTO);
        if (createdHeader != null) {
            return ResponseEntity.ok(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Header created successfully")
                    .data(createdHeader)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create header")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<HeaderDTO>> updateHeader(@RequestBody HeaderDTO headerDTO) {
        HeaderDTO updatedHeader = headerService.update(headerDTO);
        if (updatedHeader != null) {
            return ResponseEntity.ok(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Header updated successfully")
                    .data(updatedHeader)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Header not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<HeaderDTO>> deleteHeader(@RequestParam Long id) {
        HeaderDTO deletedHeader = headerService.delete(id);
        if (deletedHeader != null) {
            return ResponseEntity.ok(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Header deleted successfully")
                    .data(deletedHeader)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Header not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<HeaderDTO>> findById(@RequestParam Long id) {
        HeaderDTO header = headerService.findById(id);
        if (header != null) {
            return ResponseEntity.ok(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Header found")
                    .data(header)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<HeaderDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Header not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<HeaderDTO>>> findByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size) {
        List<HeaderDTO> headers = headerService.findByApiId(apiId, PageRequest.of(page, size));
        return ResponseEntity.ok(ResponseTemplate.<List<HeaderDTO>>builder()
                .status(HttpStatus.OK)
                .message("Headers found")
                .data(headers)
                .build());
    }

}
