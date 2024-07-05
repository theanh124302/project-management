package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.DailyReportDTO;
import com.example.projectmanagement.entity.DailyReport;
import com.example.projectmanagement.repository.DailyReportRepository;
import com.example.projectmanagement.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyReportServiceImpl implements DailyReportService {

    @Autowired
    private DailyReportRepository dailyReportRepository;

    @Override
    public DailyReportDTO create(DailyReportDTO dailyReportDTO) {
        DailyReport dailyReport = convertToEntity(dailyReportDTO);
        dailyReport.setDate(Timestamp.from(Instant.now()));
        return convertToDTO(dailyReportRepository.save(dailyReport));
    }

    @Override
    public DailyReportDTO update(DailyReportDTO dailyReportDTO) {
        Optional<DailyReport> existingDailyReportOptional = dailyReportRepository.findById(dailyReportDTO.getId());
        if (existingDailyReportOptional.isPresent()) {
            DailyReport existingDailyReport = existingDailyReportOptional.get();
            existingDailyReport.setId(dailyReportDTO.getId());
            existingDailyReport.setName(dailyReportDTO.getName());
            existingDailyReport.setDescription(dailyReportDTO.getDescription());
            existingDailyReport.setProjectId(dailyReportDTO.getProjectId());
            existingDailyReport.setCreatedBy(dailyReportDTO.getCreatedBy());
            existingDailyReport.setDate(Timestamp.from(Instant.now()));
            return convertToDTO(dailyReportRepository.save(existingDailyReport));
        } else {
            return null;
        }
    }

    @Override
    public DailyReportDTO delete(Long id) {
        Optional<DailyReport> existingDailyReportOptional = dailyReportRepository.findById(id);
        if (existingDailyReportOptional.isPresent()) {
            DailyReport existingDailyReport = existingDailyReportOptional.get();
            dailyReportRepository.delete(existingDailyReport);
            return convertToDTO(existingDailyReport);
        } else {
            return null;
        }
    }

    @Override
    public Long countByProjectId(Long projectId) {
        return dailyReportRepository.countByProjectId(projectId);
    }

    @Override
    public DailyReportDTO findById(Long id) {
        Optional<DailyReport> dailyReportOptional = dailyReportRepository.findById(id);
        return dailyReportOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<DailyReportDTO> findAllByProjectId(Long projectId, Pageable pageable) {
        return dailyReportRepository.findAllByProjectId(projectId, pageable).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DailyReportDTO> findAllByProjectIdAndName(Long projectId, String name, Pageable pageable) {
        return dailyReportRepository.findAllByProjectIdAndName(projectId, name, pageable).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DailyReportDTO convertToDTO(DailyReport dailyReport) {
        DailyReportDTO dailyReportDTO = new DailyReportDTO();
        dailyReportDTO.setId(dailyReport.getId());
        dailyReportDTO.setName(dailyReport.getName());
        dailyReportDTO.setDescription(dailyReport.getDescription());
        dailyReportDTO.setProjectId(dailyReport.getProjectId());
        dailyReportDTO.setCreatedBy(dailyReport.getCreatedBy());
        dailyReportDTO.setDate(dailyReport.getDate());
        return dailyReportDTO;
    }

    private DailyReport convertToEntity(DailyReportDTO dailyReportDTO) {
        DailyReport dailyReport = new DailyReport();
        dailyReport.setId(dailyReportDTO.getId());
        dailyReport.setName(dailyReportDTO.getName());
        dailyReport.setDescription(dailyReportDTO.getDescription());
        dailyReport.setProjectId(dailyReportDTO.getProjectId());
        dailyReport.setCreatedBy(dailyReportDTO.getCreatedBy());
        dailyReport.setDate(dailyReportDTO.getDate());
        return dailyReport;
    }
}
