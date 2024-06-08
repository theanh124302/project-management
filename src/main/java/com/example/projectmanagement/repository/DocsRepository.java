package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Docs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocsRepository extends JpaRepository<Docs, Long> {
    Page<Docs> findByApiId(Long apiId, Pageable pageable);
}
