package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.TestCaseStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseStepRepository extends JpaRepository<TestCaseStep, Long> {
    List<TestCaseStep> findByTestCaseId(Long testCaseId);
}
