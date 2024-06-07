package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="database_server")
@Entity
public class DatabaseServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private String description;
    private String name;
    private String type;
    private String url;
    private String username;
    private String password;
}
