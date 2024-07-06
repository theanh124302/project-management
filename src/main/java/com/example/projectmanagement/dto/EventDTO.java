package com.example.projectmanagement.dto;

import com.example.projectmanagement.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private Long taskId;
    private Long projectId;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private String priority;
    private Timestamp startDate;
    private Timestamp endDate;
}
