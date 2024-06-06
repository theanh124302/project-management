package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.AuthRoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthRoleService {
    AuthRoleDTO create(AuthRoleDTO authRoleDTO);
    AuthRoleDTO update(AuthRoleDTO authRoleDTO);
    AuthRoleDTO delete(Long id);
    AuthRoleDTO findById(Long id);
    Long count();
    List<AuthRoleDTO> findByApiId(Long apiId, Pageable pageable);
}
