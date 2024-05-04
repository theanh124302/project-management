package com.example.projectmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ex")
@RequiredArgsConstructor
@RestController
public class ExampleController {

    @GetMapping()
    public ResponseEntity<String> getExampleResponse() {
        // Tạo nội dung phản hồi
        String responseBody = "Hello, this is an example response!";

        // Thiết lập mã trạng thái HTTP
        HttpStatus httpStatus = HttpStatus.OK;

        // Tạo một ResponseEntity với nội dung và mã trạng thái
        ResponseEntity<String> responseEntity = ResponseEntity.status(httpStatus).body(responseBody);

        // Trả về phản hồi
        return responseEntity;
    }
}