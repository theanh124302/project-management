package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="test_case_steps")
@Entity
public class TestCaseStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long testCaseId;
    private String description;
    private String expected;
    private String actual;
    private String status;
    private String note;
}
