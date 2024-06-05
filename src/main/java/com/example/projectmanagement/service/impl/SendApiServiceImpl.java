package com.example.projectmanagement.service.impl;
import com.example.projectmanagement.dto.ApiDTO;
import com.example.projectmanagement.enums.Method;
import com.example.projectmanagement.repository.ApiRepository;
import com.example.projectmanagement.service.ApiService;
import com.example.projectmanagement.service.SendApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendApiServiceImpl implements SendApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiService apiService;

    @Override
    public ResponseEntity<String> sendRequest(String url, String jsonBody, String token, Method method) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        if (method == Method.GET) {
            return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        }else if (method == Method.POST) {
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        }else if (method == Method.PUT) {
            return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        }else if (method == Method.DELETE) {
            return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> sendRequest(Long apiId) {
        ApiDTO apiDTO = apiService.findById(apiId);
        System.out.println(apiDTO);
        // Gửi yêu cầu đến API
        return sendRequest(apiDTO.getUrl(), apiDTO.getBodyJson(), apiDTO.getToken(), apiDTO.getMethod());
    }
}