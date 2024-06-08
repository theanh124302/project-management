package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.IssueDTO;
import com.example.projectmanagement.entity.Issue;
import com.example.projectmanagement.repository.IssueRepository;
import com.example.projectmanagement.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public IssueDTO create(IssueDTO issueDTO) {
        Issue issue = convertToEntity(issueDTO);
        issue.setCreatedAt(Timestamp.from(Instant.now()));
        return convertToDTO(issueRepository.save(issue));
    }

    @Override
    public IssueDTO update(IssueDTO issueDTO) {
        Optional<Issue> existingIssueOptional = issueRepository.findById(issueDTO.getId());
        if (existingIssueOptional.isPresent()) {
            Issue existingIssue = existingIssueOptional.get();
            existingIssue.setProjectId(issueDTO.getProjectId());
            existingIssue.setApiId(issueDTO.getApiId());
            existingIssue.setUrl(issueDTO.getUrl());
            existingIssue.setDescription(issueDTO.getDescription());
            existingIssue.setContent(issueDTO.getContent());
            existingIssue.setPriority(issueDTO.getPriority());
            existingIssue.setStatus(issueDTO.getStatus());
            if(issueDTO.getStatus().equals("solvedAt")){
                existingIssue.setSolvedAt(Timestamp.from(Instant.now()));
            }
            return convertToDTO(issueRepository.save(existingIssue));
        } else {
            return null; // Handle the case where the issue doesn't exist
        }
    }

    @Override
    public IssueDTO delete(Long id) {
        Optional<Issue> existingIssueOptional = issueRepository.findById(id);
        if (existingIssueOptional.isPresent()) {
            Issue existingIssue = existingIssueOptional.get();
            issueRepository.delete(existingIssue);
            return convertToDTO(existingIssue);
        } else {
            return null; // Handle the case where the issue doesn't exist
        }
    }

    @Override
    public IssueDTO findById(Long id) {
        Optional<Issue> existingIssueOptional = issueRepository.findById(id);
        return existingIssueOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public Page<IssueDTO> findByProjectId(Long projectId, Pageable pageable) {
        return issueRepository.findByProjectId(projectId, pageable).map(this::convertToDTO);
    }

    @Override
    public Long count() {
        return issueRepository.count();
    }

    private IssueDTO convertToDTO(Issue issue) {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(issue.getId());
        issueDTO.setProjectId(issue.getProjectId());
        issueDTO.setApiId(issue.getApiId());
        issueDTO.setUrl(issue.getUrl());
        issueDTO.setDescription(issue.getDescription());
        issueDTO.setContent(issue.getContent());
        issueDTO.setPriority(issue.getPriority());
        issueDTO.setStatus(issue.getStatus());
        issueDTO.setCreatedAt(issue.getCreatedAt());
        return issueDTO;
    }

    private Issue convertToEntity(IssueDTO issueDTO) {
        Issue issue = new Issue();
        issue.setId(issueDTO.getId());
        issue.setProjectId(issueDTO.getProjectId());
        issue.setApiId(issueDTO.getApiId());
        issue.setUrl(issueDTO.getUrl());
        issue.setDescription(issueDTO.getDescription());
        issue.setContent(issueDTO.getContent());
        issue.setPriority(issueDTO.getPriority());
        issue.setStatus(issueDTO.getStatus());
        issue.setCreatedAt(issueDTO.getCreatedAt());
        return issue;
    }

}
