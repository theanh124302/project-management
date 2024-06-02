package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Param, Long> {
    Page<Param> findByApiId(Long apiId, Pageable pageable);
}
