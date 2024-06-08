package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="issue")
@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private String apiId;
    private String description;
    private String content;
    private String url;
    private String status;
    private String priority;
    private String createdBy;
    private Timestamp createdAt;
    private Timestamp solvedAt;
}
