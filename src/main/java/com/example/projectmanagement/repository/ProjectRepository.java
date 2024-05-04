package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Project;
import com.example.projectmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("SELECT p FROM Project p WHERE lower(p.name) LIKE lower(concat('%', :name, '%'))")
    Page<Project> findByName(String name, Pageable pageable);
    Page<Project> findByLeaderId(Long leaderId, Pageable pageable);
    Page<Project> findByWorkspaceId(Long workspaceId, Pageable pageable);
}
