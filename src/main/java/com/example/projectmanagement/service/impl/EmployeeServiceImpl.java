package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.CreateEmployee;
import com.example.projectmanagement.enums.Role;
import com.example.projectmanagement.entity.Employee;
import com.example.projectmanagement.repository.EmployeeRepository;
import com.example.projectmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveOrUpdateEmployee(CreateEmployee createEmployee) {
        Employee employee = new Employee();
        employee.setName(createEmployee.getName());
        employee.setGender(createEmployee.getGender());
        employee.setDepartmentId(createEmployee.getDepartmentId());
        employee.setDateOfBirth(createEmployee.getDateOfBirth());
        employee.setRole(Role.USER);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }
}
