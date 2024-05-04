package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.FolderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FolderService {
    FolderDTO create(FolderDTO folderDTO);
    FolderDTO update(FolderDTO folderDTO);
    FolderDTO delete(FolderDTO folderDTO);
    FolderDTO findById(Long id);
    List<FolderDTO> getAllFolders(Pageable pageable);
    List<FolderDTO> findByName(String name, Pageable pageable);
    List<FolderDTO> findByParentId(Long parentId, Pageable pageable);
    List<FolderDTO> findByProjectId(Long projectId, Pageable pageable);
    Long count();
}
