package com.example.projectmanagement.service.impl;
import com.example.projectmanagement.dto.FileDTO;
import com.example.projectmanagement.entity.FileEntity;
import com.example.projectmanagement.repository.FileRepository;
import com.example.projectmanagement.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

//    @Value("${file.upload-dir}")
//    private String uploadDir;

    @Value("${file.path}")
    private String filePath;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void saveFile(MultipartFile file) {
        String directory = System.getProperty("user.dir") + "\\" + filePath;
        System.out.println("Directory: " + directory);
        try {
            file.transferTo(new File(directory + "\\" + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileDTO getFile(Long id) {
        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));

        return mapToFileDTO(fileEntity);
    }

    @Override
    public List<FileDTO> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(this::mapToFileDTO)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] getFileData(Long id) {
        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
        return fileEntity.getData();
    }

    private FileDTO mapToFileDTO(FileEntity fileEntity) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(fileEntity.getId());
        fileDTO.setName(fileEntity.getName());
        fileDTO.setType(fileEntity.getType());
        fileDTO.setUrl("/api/files/" + fileEntity.getId());
        return fileDTO;
    }
}
