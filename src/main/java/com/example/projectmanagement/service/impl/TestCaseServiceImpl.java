package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.TestCaseDTO;
import com.example.projectmanagement.dto.TestCaseStepDTO;
import com.example.projectmanagement.entity.TestCase;
import com.example.projectmanagement.entity.TestCaseStep;
import com.example.projectmanagement.repository.TestCaseRepository;
import com.example.projectmanagement.repository.TestCaseStepRepository;
import com.example.projectmanagement.service.TestCaseService;
import com.example.projectmanagement.service.TestCaseStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public TestCaseDTO create(TestCaseDTO testCaseDTO) {
        TestCase testCase = convertToEntity(testCaseDTO);
        return convertToDTO(testCaseRepository.save(testCase));
    }

    @Override
    public TestCaseDTO update(TestCaseDTO testCaseDTO) {
        Optional<TestCase> existingTestCaseOptional = testCaseRepository.findById(testCaseDTO.getId());
        if (existingTestCaseOptional.isPresent()) {
            TestCase existingTestCase = existingTestCaseOptional.get();
            existingTestCase.setApiId(testCaseDTO.getApiId());
            existingTestCase.setName(testCaseDTO.getName());
            existingTestCase.setDescription(testCaseDTO.getDescription());
            return testCaseDTO;
        } else {
            return null; // Handle the case where the test case doesn't exist
        }
    }

    @Override
    public TestCaseDTO delete(Long id) {
        Optional<TestCase> existingTestCaseOptional = testCaseRepository.findById(id);
        if (existingTestCaseOptional.isPresent()) {
            TestCase existingTestCase = existingTestCaseOptional.get();
            testCaseRepository.delete(existingTestCase);
            return convertToDTO(existingTestCase);
        } else {
            return null; // Handle the case where the test case doesn't exist
        }
    }

    @Override
    public TestCaseDTO findById(Long id) {
        Optional<TestCase> testCaseOptional = testCaseRepository.findById(id);
        return testCaseOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<TestCaseDTO> findByApiId(Long apiId) {
        List<TestCase> testCaseList = testCaseRepository.findByApiId(apiId);
        return testCaseList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private TestCaseDTO convertToDTO(TestCase testCase) {
        TestCaseDTO testCaseDTO = new TestCaseDTO();
        testCaseDTO.setId(testCase.getId());
        testCaseDTO.setApiId(testCase.getApiId());
        testCaseDTO.setName(testCase.getName());
        testCaseDTO.setDescription(testCase.getDescription());
        return testCaseDTO;
    }

    private TestCase convertToEntity(TestCaseDTO testCaseDTO) {
        TestCase testCase = new TestCase();
        testCase.setId(testCaseDTO.getId());
        testCase.setApiId(testCaseDTO.getApiId());
        testCase.setName(testCaseDTO.getName());
        testCase.setDescription(testCaseDTO.getDescription());
        return testCase;
    }
}
