package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Header;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HeaderRepository extends JpaRepository<Header, Long>{
    Page<Header> findByApiId(Long apiId, Pageable pageable);
}
