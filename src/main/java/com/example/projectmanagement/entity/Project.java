package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="projects")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long leaderId;
    private Long workspaceId;
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
}
