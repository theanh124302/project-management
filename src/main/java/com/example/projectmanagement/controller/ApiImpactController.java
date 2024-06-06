package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ApiImpactDTO;
import com.example.projectmanagement.service.ApiImpactService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/impact")
public class ApiImpactController {

    @Autowired
    private ApiImpactService apiImpactService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<ApiImpactDTO>> createApiImpact(@RequestBody ApiImpactDTO apiImpactDTO) {
        ApiImpactDTO createdApiImpact = apiImpactService.create(apiImpactDTO);
        if (createdApiImpact != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("ApiImpact created successfully")
                    .data(createdApiImpact)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create apiImpact")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<ApiImpactDTO>> updateApiImpact(@RequestBody ApiImpactDTO apiImpactDTO) {
        ApiImpactDTO updatedApiImpact = apiImpactService.update(apiImpactDTO);
        if (updatedApiImpact != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.OK)
                    .message("ApiImpact updated successfully")
                    .data(updatedApiImpact)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("ApiImpact not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<ApiImpactDTO>> deleteApiImpact(@RequestParam Long id) {
        ApiImpactDTO deletedApiImpact = apiImpactService.delete(id);
        if (deletedApiImpact != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.OK)
                    .message("ApiImpact deleted successfully")
                    .data(deletedApiImpact)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("ApiImpact not found")
                    .build());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseTemplate<ApiImpactDTO>> findApiImpactById(@PathVariable Long id) {
        ApiImpactDTO apiImpact = apiImpactService.findById(id);
        if (apiImpact != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.OK)
                    .message("ApiImpact found successfully")
                    .data(apiImpact)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiImpactDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("ApiImpact not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<ApiImpactDTO>>> findApiImpactByApiId(@RequestParam Long apiId, @RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "10") int size) {
        List<ApiImpactDTO> apiImpacts = apiImpactService.findByApiId(apiId, PageRequest.of(page, size));
        if (apiImpacts != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<ApiImpactDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("ApiImpacts found successfully")
                    .data(apiImpacts)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<ApiImpactDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("ApiImpacts not found")
                    .build());
        }
    }
}
