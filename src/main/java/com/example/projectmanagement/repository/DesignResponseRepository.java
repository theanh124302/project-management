package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.DesignResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DesignResponseRepository extends JpaRepository<DesignResponse, Long>{
    Page<DesignResponse> findByApiId(Long apiId, Pageable pageable);
}
