package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {
    @Query("SELECT a FROM Api a WHERE lower(a.name) LIKE lower(concat('%', :name, '%'))")
    Page<Api> findByName(String name, Pageable pageable);
    Page<Api> findByProjectId(Long projectId, Pageable pageable);
    Page<Api> findByFolderId(Long folderId, Pageable pageable);
    Page<Api> findByExecutorIDAndProjectId(Long executorID, Long projectId, Pageable pageable);
    Page<Api> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable);
    Page<Api> findByProjectIdAndCreatedBy(Long projectId, Long createdBy, Pageable pageable);
}
