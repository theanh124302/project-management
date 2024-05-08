package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
    Page<Environment> findByProjectId(Long projectId, Pageable pageable);
    Page<Environment> findByProjectIdAndCreatedBy(Long projectId, Long createdBy, Pageable pageable);
    Page<Environment> findByName(String name, Pageable pageable);
    Page<Environment> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable);
}
