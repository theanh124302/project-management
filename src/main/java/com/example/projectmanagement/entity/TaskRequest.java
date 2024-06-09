package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="task_request")
@Entity
public class TaskRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private String description;
    private String url;
    private String content;
    private Long userId;
    private Long taskId;
    private Timestamp createdAt;
}
