package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.AuthRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuthRoleRepository extends JpaRepository<AuthRole, Long>{
    Page<AuthRole> findByApiId(Long apiId, Pageable pageable);
}
