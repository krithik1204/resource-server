package com.college.resourceserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.college.resourceserver.dto.AnnouncementCreateRequest;
import com.college.resourceserver.dto.AnnouncementResponse;
import com.college.resourceserver.service.AnnouncementService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementsApiController {
    
    @Autowired
    AnnouncementService announcementService;
    
    @GetMapping
    public ResponseEntity<List<AnnouncementResponse>> getAllAnnouncements() {
        List<AnnouncementResponse> announcements = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(announcements);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponse> getAnnouncementById(@PathVariable Long id) {
        AnnouncementResponse announcement = announcementService.getAnnouncementById(id);
        return ResponseEntity.ok(announcement);
    }
    
    @PostMapping
    public ResponseEntity<AnnouncementResponse> createAnnouncement(@Valid @RequestBody AnnouncementCreateRequest request) {
        AnnouncementResponse announcement = announcementService.createAnnouncement(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(announcement);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementResponse> updateAnnouncement(@PathVariable Long id, @Valid @RequestBody AnnouncementCreateRequest request) {
        AnnouncementResponse announcement = announcementService.updateAnnouncement(id, request);
        return ResponseEntity.ok(announcement);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}

