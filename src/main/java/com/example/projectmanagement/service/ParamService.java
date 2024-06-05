package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ParamDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParamService {
    ParamDTO create(ParamDTO paramDTO);
    ParamDTO update(ParamDTO paramDTO);
    ParamDTO delete(Long id);
    ParamDTO findById(Long id);
    List<ParamDTO> findByApiId(Long apiId, Pageable pageable);
    Long count();
}
