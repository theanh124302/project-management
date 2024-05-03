package com.example.projectmanagement.controller;

import com.example.projectmanagement.entity.Project;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @GetMapping()
    public ResponseEntity<List<Project>> getAllProject(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }
    @PostMapping()
    public ResponseEntity<?> createProject(@RequestBody Project project){

        return ResponseEntity.ok(projectService.saveOrUpdateProject(project));
    }
    @PutMapping()
    public ResponseEntity<?> updateProject(@RequestBody Project project){
        return ResponseEntity.ok(projectService.saveOrUpdateProject(project));
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProjectById(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> getProjectByID(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Project>> getProjectByName(@RequestParam String name){
        return ResponseEntity.ok(projectService.findProjectsByName(name));
    }

}

