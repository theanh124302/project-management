package com.example.projectmanagement.dao;

import lombok.Data;

import java.sql.Date;

@Data
public class CreateEmployee {
    private Long departmentId;
    private String name;
    private String gender;
    private Date dateOfBirth;
}
