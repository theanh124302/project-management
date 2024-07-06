package com.example.projectmanagement.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class IssueDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long apiId;
    private String description;
    private String content;
    private String name;
    private String url;
    private String status;
    private String priority;
    private String createdBy;
    private Timestamp createdAt;
    private Timestamp solvedAt;
}
