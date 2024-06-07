package com.example.projectmanagement.dto;

import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.TaskStatus;
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
    private Long apiId;
    private Long issueId;
    private Long reviewerId;
    private String name;
    private String description;
    private TaskStatus status;
    private LifeCycle lifeCycle;
    private String priority;
    private String type;
    private String startDate;
    private String endDate;
    private String dueDate;
    private String createdBy;
}
