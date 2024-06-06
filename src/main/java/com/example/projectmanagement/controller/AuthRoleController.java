package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.AuthRoleDTO;
import com.example.projectmanagement.dto.BodyDTO;
import com.example.projectmanagement.service.AuthRoleService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth-role")
public class AuthRoleController {
    @Autowired
    private AuthRoleService authRoleService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<AuthRoleDTO>> createBody(@RequestBody AuthRoleDTO authRoleDTO) {
        AuthRoleDTO createdAuthRole = authRoleService.create(authRoleDTO);
        if (createdAuthRole != null) {
            return ResponseEntity.ok(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Body created successfully")
                    .data(createdAuthRole)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create body")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<AuthRoleDTO>> updateBody(@RequestBody AuthRoleDTO authRoleDTO) {
        AuthRoleDTO updatedAuthRole = authRoleService.update(authRoleDTO);
        if (updatedAuthRole != null) {
            return ResponseEntity.ok(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Body updated successfully")
                    .data(updatedAuthRole)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Body not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<AuthRoleDTO>> deleteBody(@RequestParam Long id) {
        AuthRoleDTO deletedAuthRole = authRoleService.delete(id);
        if (deletedAuthRole != null) {
            return ResponseEntity.ok(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Body deleted successfully")
                    .data(deletedAuthRole)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Body not found")
                    .build());
        }
    }

    @PostMapping("/findById")
    public ResponseEntity<ResponseTemplate<AuthRoleDTO>> findById(@RequestBody Long id) {
        AuthRoleDTO foundAuthRole = authRoleService.findById(id);
        if (foundAuthRole != null) {
            return ResponseEntity.ok(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Body found successfully")
                    .data(foundAuthRole)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<AuthRoleDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Body not found")
                    .build());
        }
    }

    @PostMapping("/count")
    public ResponseEntity<ResponseTemplate<Long>> count() {
        Long count = authRoleService.count();
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Counted successfully")
                .data(count)
                .build());
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<AuthRoleDTO>>> findByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ResponseTemplate.<List<AuthRoleDTO>>builder()
                .status(HttpStatus.OK)
                .message("Found successfully")
                .data(authRoleService.findByApiId(apiId, PageRequest.of(page, size)))
                .build());
    }
}
