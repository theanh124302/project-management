package com.example.projectmanagement.dto;

import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProjectFileDTO {
    private Long id;
    private String name;
    private String description;
    private String type;
    private String url;
    private MultipartFile file;
    private Resource resource;
}
