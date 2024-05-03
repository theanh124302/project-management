package com.example.projectmanagement.test_controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.projectmanagement.enums.Method;
import com.example.projectmanagement.service.SendApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sendApiController {

    @Autowired
    private SendApiService apiService;

    @PostMapping("/test/api/project")
    public ResponseEntity<String> createProject(@RequestBody String jsonBody) {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGVhbmgiLCJpYXQiOjE3MTQ3MjYzMjMsImV4cCI6MTcxNDcyNjQwOX0.YXoNa5P0Lkx74Ua2YN64M7idExDja2w3bdn5o7-wb3g";

        String url = "http://localhost:8080/api/project";

        return apiService.sendRequest(url, jsonBody, token, Method.POST);
        
    }

}