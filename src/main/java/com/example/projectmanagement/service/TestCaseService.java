package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.TestCaseDTO;

import java.util.List;

public interface TestCaseService {
    TestCaseDTO create(TestCaseDTO testCaseDTO);
    TestCaseDTO update(TestCaseDTO testCaseDTO);
    TestCaseDTO updateStatus(Long id, String status);
    TestCaseDTO delete(Long id);
    TestCaseDTO findById(Long id);
    List<TestCaseDTO> findByApiId(Long apiId);
}
