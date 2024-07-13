package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DocumentStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentStructureRepository extends JpaRepository<DocumentStructure, Long> {
    List<DocumentStructure> findByProjectId(Long projectId);
}
