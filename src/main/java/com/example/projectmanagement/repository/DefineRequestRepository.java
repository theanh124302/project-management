package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DefineRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DefineRequestRepository extends JpaRepository<DefineRequest, Long>{
    Page<DefineRequest> findByProjectId(Long projectId, Pageable pageable);
    Page<DefineRequest> findByUserId(Long userId, Pageable pageable);
    Page<DefineRequest> findByTaskId(Long taskId, Pageable pageable);
}
