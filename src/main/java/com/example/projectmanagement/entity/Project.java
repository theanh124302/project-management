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
    private String status;
    private Date startDate;
    private Date expectedEndDate;
    private String version;
    private String coverImage;
    private Long numberOfMembers;
}
