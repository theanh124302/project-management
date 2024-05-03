package com.example.projectmanagement.entity;


import com.example.projectmanagement.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="employees")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long departmentId;
    private String name;
    private String gender;
    private Date dateOfBirth;
    private Role role;
}
