package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Body;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyRepository extends JpaRepository<Body, Long>{
    Page<Body> findByApiId(Long apiId, Pageable pageable);
}
