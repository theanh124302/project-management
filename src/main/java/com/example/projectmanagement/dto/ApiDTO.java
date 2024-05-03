package com.example.projectmanagement.dto;

import com.example.projectmanagement.entity.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDTO {
    private Long projectId;
    private String name;
    private String description;

    public ApiDTO(Api api) {
        this.projectId = api.getProjectId();
        this.name = api.getName();
        this.description = api.getDescription();
    }
}
