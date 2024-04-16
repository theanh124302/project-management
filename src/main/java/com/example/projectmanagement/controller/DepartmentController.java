package com.example.projectmanagement.controller;


import com.example.projectmanagement.dto.Department;
import com.example.projectmanagement.dto.Project;
import com.example.projectmanagement.service.DepartmentService;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<Department>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping()
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.saveOrUpdateDepartment(department));
    }

    @PutMapping()
    public ResponseEntity<?> updateDepartment(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.saveOrUpdateDepartment(department));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartmentById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Department>> getDepartmentByID(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }
}