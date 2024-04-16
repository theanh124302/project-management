package com.example.projectmanagement.controller;
import org.springframework.http.HttpStatus;
import com.example.projectmanagement.dao.CreateEmployee;
import com.example.projectmanagement.dto.Employee;
import com.example.projectmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployee createEmployee){
        if (!isValidGender(createEmployee.getGender()) || !isValidDateOfBirth(createEmployee.getDateOfBirth())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vui lòng nhập đúng giới tính và ngày tháng năm sinh");
        }
        return ResponseEntity.ok(employeeService.saveOrUpdateEmployee(createEmployee));
    }
    @PutMapping()
    public ResponseEntity<?> updateEmployee(@RequestBody CreateEmployee createEmployee){
        if (!isValidGender(createEmployee.getGender()) || !isValidDateOfBirth(createEmployee.getDateOfBirth())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vui lòng nhập đúng giới tính và ngày tháng năm sinh");
        }
        return ResponseEntity.ok(employeeService.saveOrUpdateEmployee(createEmployee));
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeByID(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam String name){
        return ResponseEntity.ok(employeeService.findEmployeesByName(name));
    }

    private boolean isValidGender(String gender) {
        return gender != null && (gender.equals("Male") || gender.equals("Female"));
    }

    private boolean isValidDateOfBirth(Date dateOfBirth) {
        Date currentDate = new Date(System.currentTimeMillis());
        Date minDateOfBirth = Date.valueOf("1990-01-01");
        return dateOfBirth != null && dateOfBirth.before(currentDate) && dateOfBirth.after(minDateOfBirth);
    }
}
