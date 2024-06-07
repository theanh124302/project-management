package com.example.projectmanagement.entity;

import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long apiId;
    private Long issueId;
    private Long reviewerId;
    private String name;
    private String description;
    private TaskStatus status;
    private LifeCycle lifeCycle;
    private String priority;
    private String type;
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp dueDate;
    private String createdBy;
}
