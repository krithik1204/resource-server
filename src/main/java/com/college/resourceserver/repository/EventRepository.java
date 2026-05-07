package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
}
