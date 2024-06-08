package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="scheduler")
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long taskId;
    private String status;
    private String priority;
    private Timestamp startDate;
    private Timestamp endDate;
}
