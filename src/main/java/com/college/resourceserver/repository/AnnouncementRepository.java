package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    
}
