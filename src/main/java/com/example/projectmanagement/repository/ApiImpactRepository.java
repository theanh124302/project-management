package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.ApiImpact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ApiImpactRepository extends JpaRepository<ApiImpact, Long>{
    Page<ApiImpact> findByApiId(Long apiId, Pageable pageable);
}
