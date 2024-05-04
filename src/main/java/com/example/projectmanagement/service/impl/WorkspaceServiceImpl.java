package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.WorkspaceDTO;
import com.example.projectmanagement.entity.Workspace;
import com.example.projectmanagement.repository.WorkspaceRepository;
import com.example.projectmanagement.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public WorkspaceDTO create(WorkspaceDTO workspaceDTO) {
        Workspace workspace = new Workspace();
        workspace.setName(workspaceDTO.getName());
        workspace.setDescription(workspaceDTO.getDescription());
        workspace.setCreatorId(workspaceDTO.getCreatorId());
        workspace.setCreationDate(workspaceDTO.getCreationDate());
        return convertToDTO(workspaceRepository.save(workspace));
    }

    @Override
    public WorkspaceDTO update(WorkspaceDTO workspaceDTO) {
        Optional<Workspace> existingWorkspaceOptional = workspaceRepository.findById(workspaceDTO.getId());
        if (existingWorkspaceOptional.isPresent()) {
            Workspace existingWorkspace = existingWorkspaceOptional.get();
            existingWorkspace.setName(workspaceDTO.getName());
            existingWorkspace.setDescription(workspaceDTO.getDescription());
            existingWorkspace.setCreatorId(workspaceDTO.getCreatorId());
            existingWorkspace.setCreationDate(workspaceDTO.getCreationDate());
            return convertToDTO(workspaceRepository.save(existingWorkspace));
        } else {
            return null; // or throw an exception if required
        }
    }

    @Override
    public WorkspaceDTO delete(WorkspaceDTO workspaceDTO) {
        Optional<Workspace> existingWorkspaceOptional = workspaceRepository.findById(workspaceDTO.getId());
        if (existingWorkspaceOptional.isPresent()) {
            Workspace existingWorkspace = existingWorkspaceOptional.get();
            workspaceRepository.delete(existingWorkspace);
            return convertToDTO(existingWorkspace);
        } else {
            return null; // or throw an exception if required
        }
    }

    @Override
    public WorkspaceDTO findById(Long id) {
        Optional<Workspace> workspaceOptional = workspaceRepository.findById(id);
        Workspace workspace = workspaceOptional.orElse(null);
        if (workspace == null) {
            return null; // or throw an exception if required
        }
        return convertToDTO(workspace);
    }

    @Override
    public List<WorkspaceDTO> getAllWorkspaces(Pageable pageable) {
        return workspaceRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<WorkspaceDTO> findByName(String name, Pageable pageable) {
        return workspaceRepository.findByName(name, pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<WorkspaceDTO> findByCreatorId(Long leaderId, Pageable pageable) {
        return workspaceRepository.findByCreatorId(leaderId, pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return workspaceRepository.count();
    }

    private WorkspaceDTO convertToDTO(Workspace workspace) {
        WorkspaceDTO workspaceDTO = new WorkspaceDTO();
        workspaceDTO.setId(workspace.getId());
        workspaceDTO.setName(workspace.getName());
        workspaceDTO.setDescription(workspace.getDescription());
        workspaceDTO.setCreatorId(workspace.getCreatorId());
        workspaceDTO.setCreationDate(workspace.getCreationDate());
        return workspaceDTO;
    }
}