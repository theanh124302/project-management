package com.example.projectmanagement.dto;

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
    private String status;
    private String priority;
    private Timestamp startDate;
    private Timestamp endDate;
}
