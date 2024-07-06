package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.EventDTO;
import com.example.projectmanagement.entity.Event;
import com.example.projectmanagement.repository.EventRepository;
import com.example.projectmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventDTO create(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);
        return convertToDTO(eventRepository.save(event));
    }

    @Override
    public EventDTO update(EventDTO eventDTO) {
        Optional<Event> existingEventOptional = eventRepository.findById(eventDTO.getId());
        if (existingEventOptional.isPresent()) {
            Event existingEvent = existingEventOptional.get();
            existingEvent.setName(eventDTO.getName());
            existingEvent.setDescription(eventDTO.getDescription());
            existingEvent.setProjectId(eventDTO.getProjectId());
            existingEvent.setPriority(eventDTO.getPriority());
            existingEvent.setStartDate(eventDTO.getStartDate());
            existingEvent.setEndDate(eventDTO.getEndDate());
            existingEvent.setTaskId(eventDTO.getTaskId());
            existingEvent.setStatus(eventDTO.getStatus());
            return convertToDTO(eventRepository.save(existingEvent));
        } else {
            return null;
        }
    }

    @Override
    public EventDTO findById(Long id) {
        Optional<Event> existingEventOptional = eventRepository.findById(id);
        return existingEventOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public EventDTO findByTaskId(Long taskId) {
        Optional<Event> existingEventOptional = eventRepository.findByTaskId(taskId);
        return existingEventOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public EventDTO delete(Long id) {
        Optional<Event> existingEventOptional = eventRepository.findById(id);
        if (existingEventOptional.isPresent()) {
            Event existingEvent = existingEventOptional.get();
            eventRepository.delete(existingEvent);
            return convertToDTO(existingEvent);
        } else {
            return null;
        }
    }

    @Override
    public List<EventDTO> findAllByProjectId(Long projectId, Pageable pageable) {
        return eventRepository.findAllByProjectId(projectId, pageable).stream().map(this::convertToDTO).toList();
    }

    @Override
    public Long count() {
        return eventRepository.count();
    }

    private EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setProjectId(event.getProjectId());
        eventDTO.setPriority(event.getPriority());
        eventDTO.setStartDate(event.getStartDate());
        eventDTO.setEndDate(event.getEndDate());
        eventDTO.setTaskId(event.getTaskId());
        eventDTO.setStatus(event.getStatus());
        return eventDTO;
    }

    private Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setProjectId(eventDTO.getProjectId());
        event.setPriority(eventDTO.getPriority());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());
        event.setTaskId(eventDTO.getTaskId());
        event.setStatus(eventDTO.getStatus());
        return event;
    }
}
