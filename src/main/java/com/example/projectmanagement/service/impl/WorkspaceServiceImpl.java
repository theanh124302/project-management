package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.entity.Workspace;
import com.example.projectmanagement.repository.WorkspaceRepository;
import com.example.projectmanagement.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;
    @Override
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    @Override
    public Optional<Workspace> getWorkspaceById(Long id) {
        return workspaceRepository.findById(id);
    }

    @Override
    public Workspace saveOrUpdateWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    @Override
    public void deleteWorkspaceById(Long id) {
        workspaceRepository.deleteById(id);
    }

}
