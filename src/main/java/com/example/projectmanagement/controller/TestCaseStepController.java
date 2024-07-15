package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.FolderDTO;
import com.example.projectmanagement.dto.TestCaseStepDTO;
import com.example.projectmanagement.service.FolderService;
import com.example.projectmanagement.service.TestCaseStepService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testCaseStep")
public class TestCaseStepController {

    @Autowired
    private TestCaseStepService testCaseStepService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<TestCaseStepDTO>> createTestCaseStep(@RequestBody TestCaseStepDTO testCaseStepDTO) {
        TestCaseStepDTO createdTestCaseStep = testCaseStepService.create(testCaseStepDTO);
        if (createdTestCaseStep != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("TestCaseStep created successfully")
                    .data(createdTestCaseStep)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create testCaseStep")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<TestCaseStepDTO>> updateTestCaseStep(@RequestBody TestCaseStepDTO testCaseStepDTO) {
        TestCaseStepDTO updatedTestCaseStep = testCaseStepService.update(testCaseStepDTO);
        if (updatedTestCaseStep != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.OK)
                    .message("TestCaseStep updated successfully")
                    .data(updatedTestCaseStep)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("TestCaseStep not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<TestCaseStepDTO>> deleteTestCaseStep(@RequestParam Long id) {
        TestCaseStepDTO deletedTestCaseStep = testCaseStepService.delete(id);
        if (deletedTestCaseStep != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.OK)
                    .message("TestCaseStep deleted successfully")
                    .data(deletedTestCaseStep)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("TestCaseStep not found")
                    .build());
        }
    }

    @GetMapping("/findByTestCaseId")
    public ResponseEntity<ResponseTemplate<List<TestCaseStepDTO>>> findTestCaseStepsByTestCaseId(@RequestParam Long testCaseId) {
        List<TestCaseStepDTO> testCaseSteps = testCaseStepService.findByTestCaseId(testCaseId);
        return ResponseEntity.ok(ResponseTemplate.<List<TestCaseStepDTO>>builder()
                .status(HttpStatus.OK)
                .message("TestCaseSteps found successfully")
                .data(testCaseSteps)
                .build());
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<TestCaseStepDTO>> findTestCaseStepById(@RequestParam Long id) {
        TestCaseStepDTO testCaseStep = testCaseStepService.findById(id);
        if (testCaseStep != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.OK)
                    .message("TestCaseStep found successfully")
                    .data(testCaseStep)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TestCaseStepDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("TestCaseStep not found")
                    .build());
        }
    }
}
