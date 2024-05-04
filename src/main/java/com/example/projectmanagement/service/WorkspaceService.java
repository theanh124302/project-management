package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.WorkspaceDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkspaceService {
    WorkspaceDTO create(WorkspaceDTO workspaceDTO);
    WorkspaceDTO update(WorkspaceDTO workspaceDTO);
    WorkspaceDTO delete(WorkspaceDTO workspaceDTO);
    WorkspaceDTO findById(Long id);
    List<WorkspaceDTO> getAllWorkspaces(Pageable pageable);
    List<WorkspaceDTO> findByName(String name, Pageable pageable);
    List<WorkspaceDTO> findByCreatorId(Long leaderId, Pageable pageable);
    Long count();
}
