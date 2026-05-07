package com.college.resourceserver.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.EventCreateRequest;
import com.college.resourceserver.dto.EventResponse;
import com.college.resourceserver.entities.Event;
import com.college.resourceserver.entities.User;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.EventRepository;
import com.college.resourceserver.repository.UserRepository;
import com.college.resourceserver.service.EventService;

@Service
public class EventServiceImpl implements EventService {
    
    @Autowired
    EventRepository eventRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<EventResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return entityDtoMapper.toEventResponseList(events);
    }
    
    @Override
    public EventResponse getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        return entityDtoMapper.toEventResponse(event);
    }
    
    @Override
    public EventResponse createEvent(EventCreateRequest request) {
        Event event = entityDtoMapper.toEventEntity(request);
        event.setCreatedAt(LocalDateTime.now());
        
        if (request.getCreatedByUserId() != null) {
            User user = userRepository.findById(request.getCreatedByUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            event.setCreatedByUser(user);
        }
        
        Event savedEvent = eventRepository.save(event);
        return entityDtoMapper.toEventResponse(savedEvent);
    }
    
    @Override
    public EventResponse updateEvent(Long eventId, EventCreateRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        
        if (request.getName() != null) {
            event.setName(request.getName());
        }
        if (request.getDescription() != null) {
            event.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            event.setStatus(Event.Status.valueOf(request.getStatus()));
        }
        if (request.getCreatedByUserId() != null) {
            User user = userRepository.findById(request.getCreatedByUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            event.setCreatedByUser(user);
        }
        
        Event updatedEvent = eventRepository.save(event);
        return entityDtoMapper.toEventResponse(updatedEvent);
    }
    
    @Override
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        eventRepository.delete(event);
    }
}
