package com.example.projectmanagement.test_controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.projectmanagement.enums.Method;
import com.example.projectmanagement.service.SendApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testSendApiController {

    @Autowired
    private SendApiService apiService;

    @PostMapping("/test/api/project")
    public ResponseEntity<String> createProject(@RequestBody String jsonBody) {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGVhbmgiLCJpYXQiOjE3MTc0MzE3ODAsImV4cCI6MTcxNzQzMTg2Nn0.ZTQzvXUdTSv7iMzzfIoQGPVge1IC-l7QdcobAUQEB1Q";

        String url = "http://localhost:8080/api/v1/project/create";

        return apiService.sendRequest(url, jsonBody, token, Method.POST);
    }
}