package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.IssueDTO;
import com.example.projectmanagement.service.IssueService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<IssueDTO>> createIssue(@RequestBody IssueDTO issueDTO) {
        IssueDTO createdIssue = issueService.create(issueDTO);
        if (createdIssue != null) {
            return ResponseEntity.ok(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Issue created successfully")
                    .data(createdIssue)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create issue")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<IssueDTO>> updateIssue(@RequestBody IssueDTO issueDTO) {
        IssueDTO updatedIssue = issueService.update(issueDTO);
        if (updatedIssue != null) {
            return ResponseEntity.ok(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Issue updated successfully")
                    .data(updatedIssue)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Issue not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<IssueDTO>> deleteIssue(@RequestParam Long id) {
        IssueDTO deletedIssue = issueService.delete(id);
        if (deletedIssue != null) {
            return ResponseEntity.ok(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Issue deleted successfully")
                    .data(deletedIssue)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Issue not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<IssueDTO>> findIssueById(@RequestParam Long id) {
        IssueDTO issue = issueService.findById(id);
        if (issue != null) {
            return ResponseEntity.ok(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Issue found successfully")
                    .data(issue)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<IssueDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Issue not found")
                    .build());
        }
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<IssueDTO>>> findIssueByProjectId(@RequestParam Long projectId, @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        List<IssueDTO> issues = issueService.findByProjectId(projectId, PageRequest.of(page, size)).getContent();
        if (!issues.isEmpty()) {
            return ResponseEntity.ok(ResponseTemplate.<List<IssueDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Issues found successfully")
                    .data(issues)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<IssueDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Issues not found")
                    .build());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<ResponseTemplate<Long>> countIssue() {
        Long count = issueService.count();
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Issue count")
                .data(count)
                .build());
    }

}
