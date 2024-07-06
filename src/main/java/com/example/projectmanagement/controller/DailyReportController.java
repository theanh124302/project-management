package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.CommentDTO;
import com.example.projectmanagement.dto.DailyReportDTO;
import com.example.projectmanagement.service.DailyReportService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/daily-report")
public class DailyReportController {

    @Autowired
    private DailyReportService dailyReportService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate> createDailyReport(@RequestBody DailyReportDTO dailyReportDTO) {
        DailyReportDTO createdDailyReport = dailyReportService.create(dailyReportDTO);
        if (createdDailyReport != null) {
            return ResponseEntity.ok(ResponseTemplate.builder()
                    .status(HttpStatus.CREATED)
                    .message("Daily report created successfully")
                    .data(createdDailyReport)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create daily report")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate> updateDailyReport(@RequestBody DailyReportDTO dailyReportDTO) {
        DailyReportDTO updatedDailyReport = dailyReportService.update(dailyReportDTO);
        if (updatedDailyReport != null) {
            return ResponseEntity.ok(ResponseTemplate.builder()
                    .status(HttpStatus.OK)
                    .message("Daily report updated successfully")
                    .data(updatedDailyReport)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Daily report not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate> deleteDailyReport(@RequestParam Long id) {
        DailyReportDTO deletedDailyReport = dailyReportService.delete(id);
        if (deletedDailyReport != null) {
            return ResponseEntity.ok(ResponseTemplate.builder()
                    .status(HttpStatus.OK)
                    .message("Daily report deleted successfully")
                    .data(deletedDailyReport)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Daily report not found")
                    .build());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate> findDailyReportById(@RequestParam Long id) {
        DailyReportDTO dailyReport = dailyReportService.findById(id);
        if (dailyReport != null) {
            return ResponseEntity.ok(ResponseTemplate.builder()
                    .status(HttpStatus.OK)
                    .message("Daily report found")
                    .data(dailyReport)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Daily report not found")
                    .build());
        }
    }

    @GetMapping("/findAllByProjectId")
    public ResponseEntity<ResponseTemplate<List<DailyReportDTO>>> findAllDailyReportsByProjectId(@RequestParam Long projectId,
                                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                                 @RequestParam(defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<DailyReportDTO> dailyReports = dailyReportService.findAllByProjectId(projectId, pageable);
        if (dailyReports != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<DailyReportDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Daily reports found")
                    .data(dailyReports)
                    .page(page)
                    .totalPages((dailyReportService.countByProjectId(projectId).intValue() / size) + 1)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<DailyReportDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Daily reports not found")
                    .page(page)
                    .totalPages((dailyReportService.countByProjectId(projectId).intValue() / size) + 1)
                    .build());
        }
    }

    @GetMapping("/findAllByProjectIdAndName")
    public ResponseEntity<ResponseTemplate<List<DailyReportDTO>>> findAllDailyReportsByProjectIdAndName(@RequestParam Long projectId, @RequestParam String name,
                                                                                                        @RequestParam(defaultValue = "0") int page,
                                                                                                        @RequestParam(defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<DailyReportDTO> dailyReports = dailyReportService.findAllByProjectIdAndName(projectId, name, pageable);
        if (dailyReports != null) {
            return ResponseEntity.ok(ResponseTemplate.<List<DailyReportDTO>>builder()
                    .status(HttpStatus.OK)
                    .message("Daily reports found")
                    .data(dailyReports)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<List<DailyReportDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Daily reports not found")
                    .build());
        }
    }

    @GetMapping("/countByProjectId")
    public ResponseEntity<ResponseTemplate> countDailyReportsByProjectId(@RequestParam Long projectId) {
        Long count = dailyReportService.countByProjectId(projectId);
        return ResponseEntity.ok(ResponseTemplate.builder()
                .status(HttpStatus.OK)
                .message("Daily reports count")
                .data(count)
                .build());
    }

}
