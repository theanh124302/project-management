package com.example.projectmanagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileDTO {
    private Long id;
    private String name;
    private String type;
    private String url;
    private String description;
}