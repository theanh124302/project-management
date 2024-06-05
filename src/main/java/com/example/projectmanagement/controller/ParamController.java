package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ParamDTO;
import com.example.projectmanagement.service.ParamService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/param")
public class ParamController {

    @Autowired
    private ParamService paramService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<ParamDTO>> createParam(@RequestBody ParamDTO paramDTO) {
        ParamDTO createdParam = paramService.create(paramDTO);
        if (createdParam != null) {
            return ResponseEntity.ok(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Param created successfully")
                    .data(createdParam)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create param")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<ParamDTO>> updateParam(@RequestBody ParamDTO paramDTO) {
        ParamDTO updatedParam = paramService.update(paramDTO);
        if (updatedParam != null) {
            return ResponseEntity.ok(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Param updated successfully")
                    .data(updatedParam)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Param not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<ParamDTO>> deleteParam(@RequestParam Long id) {
        ParamDTO deletedParam = paramService.delete(id);
        if (deletedParam != null) {
            return ResponseEntity.ok(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Param deleted successfully")
                    .data(deletedParam)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Param not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<ParamDTO>> findParamById(@RequestParam Long id) {
        ParamDTO param = paramService.findById(id);
        if (param != null) {
            return ResponseEntity.ok(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Param found successfully")
                    .data(param)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ParamDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Param not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<ParamDTO>>> findParamByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size){
        List<ParamDTO> params = paramService.findByApiId(apiId, PageRequest.of(page, size));
        if (params != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<ParamDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Param found successfully")
                    .data(params)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<ParamDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Param not found")
                    .build());
        }
    }
}
