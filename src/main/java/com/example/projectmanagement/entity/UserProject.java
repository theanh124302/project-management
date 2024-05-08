package com.example.projectmanagement.entity;


import com.example.projectmanagement.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="user_project")
@Entity
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long projectId;
    private ProjectRole role;
}
