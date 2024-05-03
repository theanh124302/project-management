package com.example.projectmanagement.service;

import com.example.projectmanagement.enums.Method;
import org.springframework.http.ResponseEntity;

public interface SendApiService {
    ResponseEntity<String> sendRequest(String url, String jsonBody, String token, Method method);
}
