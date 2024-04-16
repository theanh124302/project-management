package com.example.projectmanagement.repository;

import com.example.projectmanagement.dto.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject,Long> {

}
