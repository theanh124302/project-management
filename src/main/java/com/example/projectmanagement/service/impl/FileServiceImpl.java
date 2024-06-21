//package com.example.projectmanagement.service.impl;
//import com.example.projectmanagement.dto.FileDTO;
//import com.example.projectmanagement.entity.FileEntity;
//import com.example.projectmanagement.repository.FileRepository;
//import com.example.projectmanagement.service.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FileServiceImpl implements FileService {
//
//    @Value("${image.upload.dir}")
//    private String uploadDir;
//
//    @Value("${file.path}")
//    private String filePath;
//
//    @Autowired
//    private FileRepository fileRepository;
//
//    @Override
//    public void saveFile(MultipartFile file) {
//        String directory = uploadDir;
//        System.out.println("Directory: " + directory);
//        try {
//            file.transferTo(new File(directory + "\\" + file.getOriginalFilename()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Resource getFileData(String fileName) {
//        String directory = uploadDir + "\\" + fileName;
//        Path path = Paths.get(directory);
//        System.out.println("Path: " + path);
//        System.out.println("Path to URI: " + path.toUri());
//        try {
//            return new UrlResource(path.toUri());
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
