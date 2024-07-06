package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.ProjectFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {
    Page<ProjectFile> findByProjectId(Long projectId, Pageable pageable);
    Long countByProjectId(Long projectId);
}
