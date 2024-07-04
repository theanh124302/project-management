package com.example.projectmanagement.service;

import com.example.projectmanagement.enums.Method;
import org.springframework.http.ResponseEntity;

public interface SendApiService {
    ResponseEntity<String> sendRequest(String url, String environmentUrl, String jsonBody, String token, Method method, String parameters);
    ResponseEntity<String> sendRequest(Long apiId);
}
