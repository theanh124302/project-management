package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.DailyReportDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DailyReportService {
    DailyReportDTO create(DailyReportDTO dailyReportDTO);
    DailyReportDTO update(DailyReportDTO dailyReportDTO);
    DailyReportDTO findById(Long id);
    DailyReportDTO delete(Long id);
    List<DailyReportDTO> findAllByProjectId(Long projectId, Pageable pageable);
    List<DailyReportDTO> findAllByProjectIdAndName(Long projectId, String name, Pageable pageable);
}
