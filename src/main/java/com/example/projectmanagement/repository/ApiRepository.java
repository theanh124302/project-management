package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {
    List<Api> findByProjectId(Long projectId);

}
