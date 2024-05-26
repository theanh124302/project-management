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
    private Date creationDate;
    private String status;
    private Date startDate;
    private Date expectedEndDate;
    private String notes;
    private String version;
    private String platform;
    private String copyright;
    private String tags;
    private String coverImage;
    private String sourceCode;
    private Long numberOfMembers;
}
