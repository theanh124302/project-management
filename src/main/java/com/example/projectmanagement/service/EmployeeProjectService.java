package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.EmployeeProject;

import java.util.List;
import java.util.Optional;

public interface EmployeeProjectService {
    public void saveOrUpdateEmployeeProject(EmployeeProject employeeProject);
    public void deleteEmployeeProjectById(Long id);
}
