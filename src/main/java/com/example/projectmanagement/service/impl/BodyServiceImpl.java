package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.BodyDTO;
import com.example.projectmanagement.entity.Body;
import com.example.projectmanagement.repository.BodyRepository;
import com.example.projectmanagement.service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodyServiceImpl implements BodyService {
    @Autowired
    private BodyRepository bodyRepository;

    @Override
    public BodyDTO create(BodyDTO bodyDTO) {
        Body body = convertToEntity(bodyDTO);
        return convertToDTO(bodyRepository.save(body));
    }

    @Override
    public BodyDTO update(BodyDTO bodyDTO) {
        Optional<Body> existingBodyOptional = bodyRepository.findById(bodyDTO.getId());
        if (existingBodyOptional.isPresent()) {
            Body existingBody = existingBodyOptional.get();
            existingBody.setBodyKey(bodyDTO.getBodyKey());
            existingBody.setType(bodyDTO.getType());
            existingBody.setDescription(bodyDTO.getDescription());
            existingBody.setApiId(bodyDTO.getApiId());
            existingBody.setSample(bodyDTO.getSample());
            return convertToDTO(bodyRepository.save(existingBody));
        } else {
            return null;
        }
    }

    @Override
    public BodyDTO delete(Long id, Long deletedBy) {
        Optional<Body> existingBodyOptional = bodyRepository.findById(id);
        if (existingBodyOptional.isPresent()) {
            Body existingBody = existingBodyOptional.get();
            bodyRepository.delete(existingBody);
            return convertToDTO(existingBody);
        } else {
            return null;
        }
    }

    @Override
    public BodyDTO findById(Long id) {
        Optional<Body> existingBodyOptional = bodyRepository.findById(id);
        return existingBodyOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public Long count() {
        return bodyRepository.count();
    }

    @Override
    public List<BodyDTO> findByApiId(Long apiId, Pageable pageable) {
        return bodyRepository.findByApiId(apiId, pageable).stream().map(this::convertToDTO).toList();
    }

    private Body convertToEntity(BodyDTO bodyDTO) {
        Body body = new Body();
        body.setId(bodyDTO.getId());
        body.setBodyKey(bodyDTO.getBodyKey());
        body.setType(bodyDTO.getType());
        body.setDescription(bodyDTO.getDescription());
        body.setApiId(bodyDTO.getApiId());
        body.setSample(bodyDTO.getSample());
        return body;
    }

    private BodyDTO convertToDTO(Body body) {
        BodyDTO bodyDTO = new BodyDTO();
        bodyDTO.setId(body.getId());
        bodyDTO.setBodyKey(body.getBodyKey());
        bodyDTO.setType(body.getType());
        bodyDTO.setDescription(body.getDescription());
        bodyDTO.setApiId(body.getApiId());
        bodyDTO.setSample(body.getSample());
        return bodyDTO;
    }
}
