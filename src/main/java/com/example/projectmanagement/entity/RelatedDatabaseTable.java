package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="related_table")
@Entity
public class RelatedDatabaseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long apiId;
    private Long databaseTableId;
    private String databaseTableUuid;
    private String databaseTableName;
    private String description;
}