package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    Page<Response> findByApiId(Long apiId, Pageable pageable);
}
