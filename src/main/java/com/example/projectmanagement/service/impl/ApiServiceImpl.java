package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.entity.Api;
import com.example.projectmanagement.repository.ApiRepository;
import com.example.projectmanagement.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiRepository apiRepository;

    @Override
    public List<Api> findAllApi() {
        return apiRepository.findAll();
    }

    @Override
    public Optional<Api> findApiById(Long id) {
        return apiRepository.findById(id);
    }

    @Override
    public Api findApiByName(String name) {
        return null;
    }

    public List<Api> findByProjectId(Long projectId) {
        return apiRepository.findByProjectId(projectId);
    }

    @Override
    public Api save(Api api) {
        return apiRepository.save(api);
    }

    @Override
    public Api update(Api api) {
        return apiRepository.save(api);
    }

    @Override
    public void deleteApiById(Long id) {
        apiRepository.deleteById(id);
    }
}
