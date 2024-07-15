package com.example.projectmanagement.dto;

import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long environmentId;
    private String parameters;
    private String bodyJson;
    private String headers;
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
