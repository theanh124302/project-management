package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.AuthRoleDTO;
import com.example.projectmanagement.entity.AuthRole;
import com.example.projectmanagement.repository.AuthRoleRepository;
import com.example.projectmanagement.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Override
    public AuthRoleDTO create(AuthRoleDTO authRoleDTO) {
        AuthRole authRole = convertToEntity(authRoleDTO);
        return convertToDTO(authRoleRepository.save(authRole));
    }

    @Override
    public AuthRoleDTO update(AuthRoleDTO authRoleDTO) {
        Optional<AuthRole> existingAuthRoleOptional = authRoleRepository.findById(authRoleDTO.getId());
        if (existingAuthRoleOptional.isPresent()) {
            AuthRole existingAuthRole = existingAuthRoleOptional.get();
            existingAuthRole.setRole(authRoleDTO.getRole());
            existingAuthRole.setApiId(authRoleDTO.getApiId());
            return convertToDTO(authRoleRepository.save(existingAuthRole));
        } else {
            return null;
        }
    }

    @Override
    public AuthRoleDTO delete(Long id) {
        Optional<AuthRole> existingAuthRoleOptional = authRoleRepository.findById(id);
        if (existingAuthRoleOptional.isPresent()) {
            AuthRole existingAuthRole = existingAuthRoleOptional.get();
            authRoleRepository.delete(existingAuthRole);
            return convertToDTO(existingAuthRole);
        } else {
            return null;
        }
    }

    @Override
    public AuthRoleDTO findById(Long id) {
        Optional<AuthRole> existingAuthRoleOptional = authRoleRepository.findById(id);
        return existingAuthRoleOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public Long count() {
        return authRoleRepository.count();
    }

    @Override
    public List<AuthRoleDTO> findByApiId(Long apiId, Pageable pageable) {
        return authRoleRepository.findByApiId(apiId, pageable).stream().map(this::convertToDTO).toList();
    }

    private AuthRole convertToEntity(AuthRoleDTO authRoleDTO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleDTO.getId());
        authRole.setRole(authRoleDTO.getRole());
        authRole.setApiId(authRoleDTO.getApiId());
        return authRole;
    }

    private AuthRoleDTO convertToDTO(AuthRole authRole) {
        AuthRoleDTO authRoleDTO = new AuthRoleDTO();
        authRoleDTO.setId(authRole.getId());
        authRoleDTO.setRole(authRole.getRole());
        authRoleDTO.setApiId(authRole.getApiId());
        return authRoleDTO;
    }

}
