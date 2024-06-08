package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.EventDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    EventDTO create(EventDTO eventDTO);
    EventDTO update(EventDTO eventDTO);
    EventDTO findById(Long id);
    EventDTO delete(Long id);
    List<EventDTO> findAllByProjectId(Long projectId, Pageable pageable);
    Long count();
}
