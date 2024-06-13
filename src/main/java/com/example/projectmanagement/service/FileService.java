package com.example.projectmanagement.service;
import com.example.projectmanagement.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileService {
    void saveFile(MultipartFile file);
    FileDTO getFile(Long id);
    List<FileDTO> getAllFiles();
    byte[] getFileData(Long id); // Add this method to get file data
}
