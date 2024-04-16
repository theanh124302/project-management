package com.example.projectmanagement.repository;

import com.example.projectmanagement.dto.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    public List<Project> findByName(String name);
}
