package com.example.projectmanagement.entity;

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
    private String name;
    private String description;
    private String url;
    @Enumerated(EnumType.STRING)
    private Method method;
    private Long createdBy;
    private LocalDateTime createdAt;
}
