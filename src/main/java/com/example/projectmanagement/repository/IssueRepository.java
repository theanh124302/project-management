package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{
    Page<Issue> findByProjectId(Long projectId, Pageable pageable);
}
