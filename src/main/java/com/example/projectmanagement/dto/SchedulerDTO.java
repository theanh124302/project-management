package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerDTO {
    private Long id;
    private String name;
    private String description;
    private Long projectId;
    private String status;
    private String type;
    private String startDate;
    private String endDate;
    private String version;
    private String coverImage;
    private Long numberOfMembers;
}
