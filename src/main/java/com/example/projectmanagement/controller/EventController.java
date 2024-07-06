package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.EventDTO;
import com.example.projectmanagement.service.EventService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<EventDTO>> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO createdEvent = eventService.create(eventDTO);
        if (createdEvent != null) {
            return ResponseEntity.ok(ResponseTemplate.<EventDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Event created successfully")
                    .data(createdEvent)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<EventDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create event")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<EventDTO>> updateEvent(@RequestBody EventDTO eventDTO) {
        EventDTO updatedEvent = eventService.update(eventDTO);
        if (updatedEvent != null) {
            return ResponseEntity.ok(ResponseTemplate.<EventDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Event updated successfully")
                    .data(updatedEvent)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<EventDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Event not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<EventDTO>> deleteEvent(@RequestParam Long id) {
        EventDTO deletedEvent = eventService.delete(id);
        if (deletedEvent != null) {
            return ResponseEntity.ok(ResponseTemplate.<EventDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Event deleted successfully")
                    .data(deletedEvent)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<EventDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Event not found")
                    .build());
        }
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<EventDTO>>> findAllByProjectId(@RequestParam Long projectId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1000") int size) {
        List<EventDTO> events = eventService.findAllByProjectId(projectId, PageRequest.of(page, size));
        return ResponseEntity.ok(ResponseTemplate.<List<EventDTO>>builder()
                .status(HttpStatus.OK)
                .message("Events retrieved successfully")
                .data(events)
                .build());
    }
}
