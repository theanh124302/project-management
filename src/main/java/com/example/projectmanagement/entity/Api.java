package com.example.projectmanagement.entity;

import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.Method;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="apis")
@Entity
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long folderId;
    private Long environmentId;
    private String name;
    private String description;
    private String url;
    @Enumerated(EnumType.STRING)
    private Method method;
    private Long createdBy;
    private LocalDateTime createdAt;
    private String status;
    private Long executorID;
    private LifeCycle lifeCycle;
}
