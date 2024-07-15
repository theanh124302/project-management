package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    List<TestCase> findByApiId(Long apiId);
}
