package com.example.projectmanagement.dto;


import com.example.projectmanagement.enums.ProjectRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserProjectDTO {
    private Long id;
    private int userId;
    private int projectId;
    private ProjectRole role;
}
