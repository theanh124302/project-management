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
    @GetMapping("/sendApi")
    public ResponseEntity<String> sendApi(@RequestParam Long apiId) {
        System.out.println("apiId: " + apiId);
        return apiService.sendRequest(apiId);
    }

}