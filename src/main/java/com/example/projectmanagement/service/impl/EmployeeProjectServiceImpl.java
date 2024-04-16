package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.EmployeeProject;
import com.example.projectmanagement.dto.Project;
import com.example.projectmanagement.repository.EmployeeProjectRepository;
import com.example.projectmanagement.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {
    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    @Override
    public void saveOrUpdateEmployeeProject(EmployeeProject employeeProject) {
        employeeProjectRepository.save(employeeProject);
    }

    @Override
    public void deleteEmployeeProjectById(Long id) {
        employeeProjectRepository.deleteById(id);
    }
}
