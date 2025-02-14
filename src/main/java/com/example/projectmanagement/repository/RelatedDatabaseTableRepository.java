package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.RelatedDatabaseTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatedDatabaseTableRepository extends JpaRepository<RelatedDatabaseTable, Long> {
    Page<RelatedDatabaseTable> findAllByApiId(Long apiId, Pageable pageable);
}