package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DatabaseTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseTableRepository extends JpaRepository<DatabaseTable, Long> {
    Page<DatabaseTable> findAllByDatabaseServerId(Long databaseServerId, Pageable pageable);
}
