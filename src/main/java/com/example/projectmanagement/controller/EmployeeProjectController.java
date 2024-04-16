package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.EmployeeProject;
import com.example.projectmanagement.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ep")
public class EmployeeProjectController {
    @Autowired
    EmployeeProjectService employeeProjectService;
    @PostMapping()
    public void createEp(@RequestBody EmployeeProject employeeProject){
        employeeProjectService.saveOrUpdateEmployeeProject(employeeProject);
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id){
        employeeProjectService.deleteEmployeeProjectById(id);
    }
}
