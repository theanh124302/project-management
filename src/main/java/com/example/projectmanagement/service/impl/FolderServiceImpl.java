package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.FolderDTO;
import com.example.projectmanagement.entity.Folder;
import com.example.projectmanagement.repository.FolderRepository;
import com.example.projectmanagement.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Override
    public FolderDTO create(FolderDTO folderDTO) {
        Folder folder = new Folder();
        folder.setName(folderDTO.getName());
        folder.setProjectId(folderDTO.getProjectId());
        return convertToDTO(folderRepository.save(folder));
    }

    @Override
    public FolderDTO update(FolderDTO folderDTO) {
        Optional<Folder> existingFolderOptional = folderRepository.findById(folderDTO.getId());
        if (existingFolderOptional.isPresent()) {
            Folder existingFolder = existingFolderOptional.get();
            existingFolder.setName(folderDTO.getName());
            existingFolder.setProjectId(folderDTO.getProjectId());
            return convertToDTO(folderRepository.save(existingFolder));
        } else {
            return null;
        }
    }

    @Override
    public FolderDTO delete(Long id) {
        Optional<Folder> existingFolderOptional = folderRepository.findById(id);
        if (existingFolderOptional.isPresent()) {
            Folder existingFolder = existingFolderOptional.get();
            folderRepository.delete(existingFolder);
            return convertToDTO(existingFolder);
        } else {
            return null;
        }
    }

    @Override
    public FolderDTO findById(Long id) {
        Optional<Folder> folderOptional = folderRepository.findById(id);
        return folderOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<FolderDTO> getAllFolders(Pageable pageable) {
        return folderRepository.findAll(pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<FolderDTO> findByName(String name, Pageable pageable) {
        return folderRepository.findByName(name, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

//    @Override
//    public List<FolderDTO> findByParentId(Long parentId, Pageable pageable) {
//        return folderRepository.findByParentId(parentId, pageable).getContent().stream()
//                .map(this::convertToDTO)
//                .toList();
//    }

    @Override
    public List<FolderDTO> findByProjectId(Long projectId, Pageable pageable) {
        return folderRepository.findByProjectId(projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return folderRepository.count();
    }

    private FolderDTO convertToDTO(Folder folder) {
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setId(folder.getId());
        folderDTO.setName(folder.getName());
        folderDTO.setProjectId(folder.getProjectId());
        return folderDTO;
    }
}
