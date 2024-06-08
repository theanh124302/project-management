package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DailyReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {
    Page<DailyReport> findAllByProjectId(Long projectId, Pageable pageable);

    @Query("SELECT d FROM DailyReport d WHERE lower(d.name) LIKE lower(concat('%', :name, '%')) And d.projectId = :projectId" )
    Page<DailyReport> findAllByProjectIdAndName(Long projectId, String name, Pageable pageable);
}
