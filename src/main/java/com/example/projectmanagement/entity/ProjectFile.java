package com.example.projectmanagement.entity;


import jakarta.persistence.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="project_file")
@Entity
public class ProjectFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private String type;
    private String uuid;
    private String localPath;
    private String url;
}
