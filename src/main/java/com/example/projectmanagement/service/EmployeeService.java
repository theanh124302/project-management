package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.CreateEmployee;
import com.example.projectmanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public Optional<Employee> getEmployeeById(Long id);
    public Employee saveOrUpdateEmployee(CreateEmployee createEmployee);
    public void deleteEmployeeById(Long id);
    public List<Employee> findEmployeesByName(String name);
}
