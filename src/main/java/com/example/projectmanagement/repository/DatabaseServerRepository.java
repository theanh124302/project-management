package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DatabaseServer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseServerRepository extends JpaRepository<DatabaseServer, Long> {
    Page<DatabaseServer> findAllByProjectId(Long projectId, Pageable pageable);
}
