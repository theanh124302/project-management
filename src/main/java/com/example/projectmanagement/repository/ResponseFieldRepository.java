package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.ResponseField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseFieldRepository extends JpaRepository<ResponseField, Long> {
    Page<ResponseField> findByResponseId(Long responseId, Pageable pageable);
    Page<ResponseField> findByFatherId(Long fatherId, Pageable pageable);
}
