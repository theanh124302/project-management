package com.example.projectmanagement.controller;
import com.example.projectmanagement.dto.FileDTO;
import com.example.projectmanagement.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam MultipartFile file) {
        System.out.println("File name: " + file.getOriginalFilename());
        fileService.saveFile(file);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
//        FileDTO fileDTO = fileService.getFile(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getName() + "\"")
//                .body(fileService.getFileData(id));
//    }
//
//    @GetMapping
//    public List<FileDTO> getAllFiles() {
//        return fileService.getAllFiles();
//    }
}
