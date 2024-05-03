package com.example.projectmanagement.service;

import com.example.projectmanagement.entity.Workspace;

import java.util.List;
import java.util.Optional;

public interface WorkspaceService {
    public List<Workspace> getAllWorkspaces();
    public Optional<Workspace> getWorkspaceById(Long id);
    public Workspace saveOrUpdateWorkspace(Workspace workspace);
    public void deleteWorkspaceById(Long id);
}
