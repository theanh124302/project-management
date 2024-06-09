package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.TaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRequestRepository extends JpaRepository<TaskRequest, Long>{
    Page<TaskRequest> findByProjectId(Long projectId, Pageable pageable);
    Page<TaskRequest> findByUserId(Long userId, Pageable pageable);
    Page<TaskRequest> findByTaskId(Long taskId, Pageable pageable);
}
