package com.example.projectmanagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FolderDTO {
    private Long id;
    private String name;
    private Long projectId;
}
