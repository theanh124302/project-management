package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.entity.UserProject;
import com.example.projectmanagement.repository.UserProjectRepository;
import com.example.projectmanagement.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {
    @Autowired
    UserProjectRepository userProjectRepository;

    @Override
    public void saveOrUpdateEmployeeProject(UserProject userProject) {
        userProjectRepository.save(userProject);
    }

    @Override
    public void deleteEmployeeProjectById(Long id) {
        userProjectRepository.deleteById(id);
    }
}
