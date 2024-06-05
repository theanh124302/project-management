package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ParamDTO;
import com.example.projectmanagement.entity.Param;
import com.example.projectmanagement.repository.ParamRepository;
import com.example.projectmanagement.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamRepository paramRepository;

    @Override
    public ParamDTO create(ParamDTO paramDTO) {
        Param param = convertToEntity(paramDTO);
        return convertToDTO(paramRepository.save(param));
    }

    @Override
    public ParamDTO update(ParamDTO paramDTO) {
        Optional<Param> existingParamOptional = paramRepository.findById(paramDTO.getId());
        if (existingParamOptional.isPresent()) {
            Param existingParam = existingParamOptional.get();
            existingParam.setParamKey(paramDTO.getParamKey());
            existingParam.setType(paramDTO.getType());
            existingParam.setDescription(paramDTO.getDescription());
            existingParam.setApiId(paramDTO.getApiId());
            existingParam.setSample(paramDTO.getSample());
            return convertToDTO(paramRepository.save(existingParam));
        } else {
            return null;
        }
    }

    @Override
    public ParamDTO delete(Long id, Long deletedBy) {
        Optional<Param> existingParamOptional = paramRepository.findById(id);
        if (existingParamOptional.isPresent()) {
            Param existingParam = existingParamOptional.get();
            paramRepository.delete(existingParam);
            return convertToDTO(existingParam);
        } else {
            return null;
        }
    }

    @Override
    public ParamDTO findById(Long id) {
        Optional<Param> existingParamOptional = paramRepository.findById(id);
        return existingParamOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<ParamDTO> findByApiId(Long apiId, Pageable pageable) {
        return paramRepository.findByApiId(apiId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return paramRepository.count();
    }

    private ParamDTO convertToDTO(Param param) {
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setId(param.getId());
        paramDTO.setParamKey(param.getParamKey());
        paramDTO.setType(param.getType());
        paramDTO.setDescription(param.getDescription());
        paramDTO.setApiId(param.getApiId());
        paramDTO.setSample(param.getSample());
        return paramDTO;
    }

    private Param convertToEntity(ParamDTO paramDTO) {
        Param param = new Param();
        param.setId(paramDTO.getId());
        param.setParamKey(paramDTO.getParamKey());
        param.setType(paramDTO.getType());
        param.setDescription(paramDTO.getDescription());
        param.setApiId(paramDTO.getApiId());
        param.setSample(paramDTO.getSample());
        return param;
    }
}