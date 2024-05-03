package com.example.projectmanagement.controller;


import com.example.projectmanagement.entity.Workspace;
import com.example.projectmanagement.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;

    @GetMapping()
    public ResponseEntity<List<Workspace>> getAllWorkspace(){
        return ResponseEntity.ok(workspaceService.getAllWorkspaces());
    }

    @PostMapping()
    public ResponseEntity<?> createWorkspace(@RequestBody Workspace workspace){
        return ResponseEntity.ok(workspaceService.saveOrUpdateWorkspace(workspace));
    }

    @PutMapping()
    public ResponseEntity<?> updateWorkspace(@RequestBody Workspace workspace){
        return ResponseEntity.ok(workspaceService.saveOrUpdateWorkspace(workspace));
    }

    @DeleteMapping("/{id}")
    public void deleteWorkspace(@PathVariable Long id){
        workspaceService.deleteWorkspaceById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Workspace>> getWorkspaceByID(@PathVariable Long id){
        return ResponseEntity.ok(workspaceService.getWorkspaceById(id));
    }
}