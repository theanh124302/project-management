package com.example.projectmanagement.service;
import com.example.projectmanagement.dto.TestCaseStepDTO;

import java.util.List;


public interface TestCaseStepService {
    TestCaseStepDTO create(TestCaseStepDTO testCaseStepDTO);
    TestCaseStepDTO update(TestCaseStepDTO testCaseStepDTO);
    TestCaseStepDTO delete(Long id);
    TestCaseStepDTO findById(Long id);
    List<TestCaseStepDTO> findByTestCaseId(Long testCaseId);
}
