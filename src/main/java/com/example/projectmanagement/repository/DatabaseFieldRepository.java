package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DatabaseField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseFieldRepository extends JpaRepository<DatabaseField, Long> {
    Page<DatabaseField> findAllByDatabaseTableId(Long databaseTableId, Pageable pageable);
}
