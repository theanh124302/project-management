package com.example.projectmanagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EnvironmentDTO {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Long projectId;
    private Long createdBy;
    private String value;
    private String createdAt;
}
