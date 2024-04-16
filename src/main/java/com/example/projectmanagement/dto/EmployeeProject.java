package com.example.projectmanagement.dto;


import com.example.projectmanagement.dao.ProjectRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="employee_project")
@Entity
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int oid;
    private int pid;
    private ProjectRole role;
}
