package com.example.projectmanagement.controller;

import com.example.projectmanagement.enums.Method;
import com.example.projectmanagement.service.SendApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/send")
@RequiredArgsConstructor
@RestController
public class SendApiController {

    @Autowired
    private SendApiService apiService;

    @PostMapping("")
    public ResponseEntity<String> createProject(@RequestBody String jsonBody) {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGVhbmgiLCJpYXQiOjE3MTc0ODc0MjgsImV4cCI6MTcxNzQ4NzUxNH0.Ai-C6Fa0-koWtaTfXT-j2LhK9dMLJSmcdRcKhwCXfcg";

        String url = "http://localhost:8080/api/v1/project/create";

        return apiService.sendRequest(url, jsonBody, token, Method.POST);
    }

    @GetMapping("/sendApi")
    public ResponseEntity<String> sendApi(@RequestParam Long apiId) {
        System.out.println("apiId: " + apiId);
        return apiService.sendRequest(apiId);
    }

}