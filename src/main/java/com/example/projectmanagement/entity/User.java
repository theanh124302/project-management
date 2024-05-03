package com.example.projectmanagement.entity;

import com.example.projectmanagement.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(nullable = false)
    private String name;
    private String phoneNumber;
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    private Long age;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
