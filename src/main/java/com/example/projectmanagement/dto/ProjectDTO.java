package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private Long leaderId;
    private String status;
    private Date startDate;
    private Date expectedEndDate;
    private String version;
    private String coverImage;
    private Long numberOfMembers;
}
