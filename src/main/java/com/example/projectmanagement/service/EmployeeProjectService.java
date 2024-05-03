package com.example.projectmanagement.service;

import com.example.projectmanagement.entity.UserProject;

public interface EmployeeProjectService {
    public void saveOrUpdateEmployeeProject(UserProject userProject);
    public void deleteEmployeeProjectById(Long id);
}
