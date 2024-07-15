package com.example.projectmanagement.entity;

import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.Method;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="apis")
@Entity
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long folderId;
    private String name;
    private String description;
    private String url;
    @Enumerated(EnumType.STRING)
    private Method method;
    private Long createdBy;
    private String status;
    private String parameters;
    private String bodyJson;
    private String headers;
    private Long environmentId;
    private String token;
    private String userRequirements;
    private String technicalRequirements;
    private String businessProcess;
    private String useCaseDiagram;
    private String sequenceDiagram;
    private String activityDiagram;
    private String classDiagram;
    private String installationGuide;
    private String testCases;
    private String testScenarios;
    private String testScripts;
    private String sourceCode;
    private String solutionDocument;
    private LifeCycle lifeCycle;
}
