package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
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
