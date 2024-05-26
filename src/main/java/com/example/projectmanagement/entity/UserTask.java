package com.example.projectmanagement.entity;

import com.example.projectmanagement.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="user_task")
@Entity
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long taskId;
}
