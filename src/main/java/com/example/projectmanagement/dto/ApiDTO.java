package com.example.projectmanagement.dto;

import com.example.projectmanagement.entity.Api;
import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.Method;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDTO {
    private Long id;
    private Long projectId;
    private Long folderId;
    private String name;
    private String description;
    private String url;
    private Method method;
    private Long createdBy;
    private String status;
    private String bodyJson;
    private String token;
    private LifeCycle lifeCycle;
}
