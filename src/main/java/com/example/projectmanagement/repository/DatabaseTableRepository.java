package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DatabaseTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatabaseTableRepository extends JpaRepository<DatabaseTable, Long> {
    Page<DatabaseTable> findAllByDatabaseServerId(Long databaseServerId, Pageable pageable);
    Optional<DatabaseTable> findByUuid(String uuid);
    Long countByProjectId(Long projectId);
}
