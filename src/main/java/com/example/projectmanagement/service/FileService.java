package com.example.projectmanagement.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void saveFile(MultipartFile file);
    public Resource getFileData(String fileName);
}
