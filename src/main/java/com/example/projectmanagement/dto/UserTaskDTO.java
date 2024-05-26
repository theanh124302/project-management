package com.example.projectmanagement.dto;

import com.example.projectmanagement.enums.ProjectRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserTaskDTO {
    private Long id;
    private Long userId;
    private Long taskId;
}
