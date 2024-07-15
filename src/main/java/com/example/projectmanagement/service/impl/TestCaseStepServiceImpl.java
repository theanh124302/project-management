package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.TaskRequestDTO;
import com.example.projectmanagement.dto.TestCaseStepDTO;
import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.entity.TaskRequest;
import com.example.projectmanagement.entity.TestCaseStep;
import com.example.projectmanagement.repository.*;
import com.example.projectmanagement.service.EmailService;
import com.example.projectmanagement.service.TaskRequestService;
import com.example.projectmanagement.service.TestCaseStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TestCaseStepServiceImpl implements TestCaseStepService {

    @Autowired
    private TestCaseStepRepository testCaseStepRepository;

    @Override
    public TestCaseStepDTO create(TestCaseStepDTO testCaseStepDTO) {
        TestCaseStep testCaseStep = convertToEntity(testCaseStepDTO);
        return convertToDTO(testCaseStepRepository.save(testCaseStep));
    }

    @Override
    public TestCaseStepDTO update(TestCaseStepDTO testCaseStepDTO) {
        Optional<TestCaseStep> existingTestCaseStepOptional = testCaseStepRepository.findById(testCaseStepDTO.getId());
        if (existingTestCaseStepOptional.isPresent()) {
            TestCaseStep existingTestCaseStep = existingTestCaseStepOptional.get();
            existingTestCaseStep.setTestCaseId(testCaseStepDTO.getTestCaseId());
            existingTestCaseStep.setStatus(testCaseStepDTO.getStatus());
            existingTestCaseStep.setExpected(testCaseStepDTO.getExpected());
            existingTestCaseStep.setActual(testCaseStepDTO.getActual());
            existingTestCaseStep.setDescription(testCaseStepDTO.getDescription());
            return testCaseStepDTO;
        } else {
            return null; // Handle the case where the test case step doesn't exist
        }
    }

    @Override
    public TestCaseStepDTO delete(Long id) {
        Optional<TestCaseStep> existingTestCaseStepOptional = testCaseStepRepository.findById(id);
        if (existingTestCaseStepOptional.isPresent()) {
            TestCaseStep existingTestCaseStep = existingTestCaseStepOptional.get();
            testCaseStepRepository.delete(existingTestCaseStep);
            return convertToDTO(existingTestCaseStep);
        } else {
            return null; // Handle the case where the test case step doesn't exist
        }
    }

    @Override
    public TestCaseStepDTO findById(Long id) {
        Optional<TestCaseStep> testCaseStepOptional = testCaseStepRepository.findById(id);
        return testCaseStepOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<TestCaseStepDTO> findByTestCaseId(Long testCaseId) {
        List<TestCaseStep> testCaseStepList = testCaseStepRepository.findByTestCaseId(testCaseId);
        return testCaseStepList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private TestCaseStepDTO convertToDTO(TestCaseStep testCaseStep) {
        TestCaseStepDTO testCaseStepDTO = new TestCaseStepDTO();
        testCaseStepDTO.setId(testCaseStep.getId());
        testCaseStepDTO.setTestCaseId(testCaseStep.getTestCaseId());
        testCaseStepDTO.setStatus(testCaseStep.getStatus());
        testCaseStepDTO.setExpected(testCaseStep.getExpected());
        testCaseStepDTO.setActual(testCaseStep.getActual());
        testCaseStepDTO.setDescription(testCaseStep.getDescription());
        return testCaseStepDTO;
    }

    private TestCaseStep convertToEntity(TestCaseStepDTO testCaseStepDTO) {
        TestCaseStep testCaseStep = new TestCaseStep();
        testCaseStep.setId(testCaseStepDTO.getId());
        testCaseStep.setTestCaseId(testCaseStepDTO.getTestCaseId());
        testCaseStep.setStatus(testCaseStepDTO.getStatus());
        testCaseStep.setExpected(testCaseStepDTO.getExpected());
        testCaseStep.setActual(testCaseStepDTO.getActual());
        testCaseStep.setDescription(testCaseStepDTO.getDescription());
        return testCaseStep;
    }

}