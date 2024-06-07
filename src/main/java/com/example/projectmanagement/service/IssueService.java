package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.IssueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueService {
    IssueDTO create(IssueDTO issueDTO);
    IssueDTO update(IssueDTO issueDTO);
    IssueDTO delete(Long id);
    IssueDTO findById(Long id);
    Page<IssueDTO> findByProjectId(Long projectId, Pageable pageable);
    Long count();
}
