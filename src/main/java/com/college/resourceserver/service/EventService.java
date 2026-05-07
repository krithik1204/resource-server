package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.EventCreateRequest;
import com.college.resourceserver.dto.EventResponse;

public interface EventService {
    
    List<EventResponse> getAllEvents();
    
    EventResponse getEventById(Long eventId);
    
    EventResponse createEvent(EventCreateRequest request);
    
    EventResponse updateEvent(Long eventId, EventCreateRequest request);
    
    void deleteEvent(Long eventId);
}
