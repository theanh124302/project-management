package com.example.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="api_impact")
@Entity
public class ApiImpact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long apiId;
    private Long impactApiId;
    private String apiImpactName;
    private String status;
    private String impactDescription;
    private String impactPriority;
    private String solution;
}
