//package com.example.projectmanagement.controller;
//import com.example.projectmanagement.dto.FileDTO;
//import com.example.projectmanagement.service.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//
//@RestController
//@RequestMapping("/api/v1/files")
//public class FileController {
//
//    @Autowired
//    private FileService fileService;
//
//    @PostMapping("/upload")
//    public void uploadFile(@RequestParam MultipartFile file) {
//        System.out.println("File name: " + file.getOriginalFilename());
//        fileService.saveFile(file);
//    }
//
//    @GetMapping("/get/{fileName}")
//    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
//        Resource file = fileService.getFileData(fileName);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(file);
//    }
//}
