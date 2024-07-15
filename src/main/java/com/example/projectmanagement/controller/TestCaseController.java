package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.TestCaseDTO;
import com.example.projectmanagement.dto.TestCaseStepDTO;
import com.example.projectmanagement.service.TestCaseService;
import com.example.projectmanagement.service.TestCaseStepService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testCase")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<TestCaseDTO>> createTestCase(@RequestBody TestCaseDTO testCaseDTO) {
        TestCaseDTO createdTestCase = testCaseService.create(testCaseDTO);
        if (createdTestCase != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("TestCase created successfully")
                    .data(createdTestCase)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create testCase")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<TestCaseDTO>> updateTestCase(@RequestBody TestCaseDTO testCaseDTO) {
        TestCaseDTO updatedTestCase = testCaseService.update(testCaseDTO);
        if (updatedTestCase != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("TestCase updated successfully")
                    .data(updatedTestCase)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("TestCase not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<TestCaseDTO>> deleteTestCase(@RequestParam Long id) {
        TestCaseDTO deletedTestCase = testCaseService.delete(id);
        if (deletedTestCase != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("TestCase deleted successfully")
                    .data(deletedTestCase)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("TestCase not found")
                    .build());
        }
    }

    @GetMapping("/findByApiId")
    public ResponseEntity<ResponseTemplate<List<TestCaseDTO>>> findTestCasesByApiId(@RequestParam Long apiId) {
        List<TestCaseDTO> testCases = testCaseService.findByApiId(apiId);
        return ResponseEntity.ok(ResponseTemplate.<List<TestCaseDTO>>builder()
                .status(HttpStatus.OK)
                .message("TestCases found successfully")
                .data(testCases)
                .build());
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<TestCaseDTO>> findTestCaseById(@RequestParam Long id) {
        TestCaseDTO testCase = testCaseService.findById(id);
        if (testCase != null) {
            return ResponseEntity.ok(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("TestCase found successfully")
                    .data(testCase)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<TestCaseDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("TestCase not found")
                    .build());
        }
    }

}