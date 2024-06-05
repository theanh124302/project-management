package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.BodyDTO;
import com.example.projectmanagement.dto.ParamDTO;
import com.example.projectmanagement.service.BodyService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/body")
public class BodyController {

    @Autowired
    private BodyService bodyService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<BodyDTO>> createBody(@RequestBody BodyDTO bodyDTO) {
        BodyDTO createdBody = bodyService.create(bodyDTO);
        if (createdBody != null) {
            return ResponseEntity.ok(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Body created successfully")
                    .data(createdBody)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create body")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<BodyDTO>> updateBody(@RequestBody BodyDTO bodyDTO) {
        BodyDTO updatedBody = bodyService.update(bodyDTO);
        if (updatedBody != null) {
            return ResponseEntity.ok(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Body updated successfully")
                    .data(updatedBody)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Body not found")
                    .build());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseTemplate<BodyDTO>> deleteBody(@RequestBody Long id) {
        BodyDTO deletedBody = bodyService.delete(id);
        if (deletedBody != null) {
            return ResponseEntity.ok(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Body deleted successfully")
                    .data(deletedBody)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Body not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<BodyDTO>> findParamById(@RequestParam Long id) {
        BodyDTO body = bodyService.findById(id);
        if (body != null) {
            return ResponseEntity.ok(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Param found successfully")
                    .data(body)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<BodyDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Param not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<BodyDTO>>> findByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<BodyDTO>>builder()
                .status(HttpStatus.OK)
                .message("Body found successfully")
                .data(bodyService.findByApiId(apiId, PageRequest.of(page, size)))
                .build());
    }
}
