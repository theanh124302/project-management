package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.DesignResponseDTO;
import com.example.projectmanagement.dto.HeaderDTO;
import com.example.projectmanagement.service.DesignResponseService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/design-response")
public class DesignResponseController {

    @Autowired
    private DesignResponseService designResponseService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<DesignResponseDTO>> createHeader(@RequestBody DesignResponseDTO designResponseDTO) {
        DesignResponseDTO createdDesignResponse = designResponseService.create(designResponseDTO);
        if (createdDesignResponse != null) {
            return ResponseEntity.ok(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Header created successfully")
                    .data(createdDesignResponse)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create header")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<DesignResponseDTO>> updateHeader(@RequestBody DesignResponseDTO designResponseDTO) {
        DesignResponseDTO updatedDesignResponse = designResponseService.update(designResponseDTO);
        if (updatedDesignResponse != null) {
            return ResponseEntity.ok(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Header updated successfully")
                    .data(updatedDesignResponse)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Header not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<DesignResponseDTO>> deleteHeader(@RequestParam Long id) {
        DesignResponseDTO deletedDesignResponse = designResponseService.delete(id);
        if (deletedDesignResponse != null) {
            return ResponseEntity.ok(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Header deleted successfully")
                    .data(deletedDesignResponse)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Header not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<DesignResponseDTO>> findHeaderById(@RequestParam Long id) {
        DesignResponseDTO designResponse = designResponseService.findById(id);
        if (designResponse != null) {
            return ResponseEntity.ok(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Header found successfully")
                    .data(designResponse)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<DesignResponseDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Header not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<DesignResponseDTO>>> findHeadersByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<DesignResponseDTO>>builder()
                .status(HttpStatus.OK)
                .message("Headers found successfully")
                .data(designResponseService.findByApiId(apiId, PageRequest.of(page, size)))
                .build());
    }
}

