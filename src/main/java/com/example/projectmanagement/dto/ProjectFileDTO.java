package com.example.projectmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long projectId;
    private String name;
    private String description;
    private String type;
    private String uuid;
    private String localPath;
    private String url;
}
