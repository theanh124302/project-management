package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedDatabaseTableDTO {
    private Long id;
    private Long apiId;
    private Long databaseTableId;
    private String databaseTableUuid;
    private String databaseTableName;
    private String description;
}