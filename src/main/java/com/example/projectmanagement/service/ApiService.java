package com.example.projectmanagement.service;

import com.example.projectmanagement.entity.Api;

import java.util.List;
import java.util.Optional;

public interface ApiService {
    List<Api> findAllApi();
    Optional<Api> findApiById(Long id);
    Api findApiByName(String name);
    Api save(Api api);
    Api update(Api api);
    void deleteApiById(Long id);
}
