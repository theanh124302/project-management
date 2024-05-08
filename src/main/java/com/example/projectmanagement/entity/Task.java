package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Long executorId;
    private Long apiId;
    private String name;
    private String description;
    private String status;
    private String priority;
    private String type;
    private String tags;
    private String notes;
    private String startDate;
    private String endDate;
    private String dueDate;
    private String progress;
    private String createdBy;
    private String createdAt;
    private String updatedAt;
    private String canceledAt;
    private String canceledBy;
    private String canceledReason;
    private String canceledNote;
}
