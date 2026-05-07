package com.college.resourceserver.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.AnnouncementCreateRequest;
import com.college.resourceserver.dto.AnnouncementResponse;
import com.college.resourceserver.entities.Announcement;
import com.college.resourceserver.entities.Role;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.AnnouncementRepository;
import com.college.resourceserver.repository.RoleRepository;
import com.college.resourceserver.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    
    @Autowired
    AnnouncementRepository announcementRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<AnnouncementResponse> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        return entityDtoMapper.toAnnouncementResponseList(announcements);
    }
    
    @Override
    public AnnouncementResponse getAnnouncementById(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + announcementId));
        return entityDtoMapper.toAnnouncementResponse(announcement);
    }
    
    @Override
    public AnnouncementResponse createAnnouncement(AnnouncementCreateRequest request) {
        Announcement announcement = entityDtoMapper.toAnnouncementEntity(request);
        announcement.setCreatedAt(LocalDateTime.now());
        if (request.getCreatedByRole() != null) {
            Role role = roleRepository.findByName(request.getCreatedByRole())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + request.getCreatedByRole()));
            announcement.setCreatedByRole(role);
        }
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return entityDtoMapper.toAnnouncementResponse(savedAnnouncement);
    }
    
    @Override
    public AnnouncementResponse updateAnnouncement(Long announcementId, AnnouncementCreateRequest request) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + announcementId));
        
        if (request.getTitle() != null) {
            announcement.setTitle(request.getTitle());
        }
        if (request.getMessage() != null) {
            announcement.setMessage(request.getMessage());
        }
        if (request.getCreatedByRole() != null) {
            Role role = roleRepository.findByName(request.getCreatedByRole())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + request.getCreatedByRole()));
            announcement.setCreatedByRole(role);
        }
        
        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return entityDtoMapper.toAnnouncementResponse(updatedAnnouncement);
    }
    
    @Override
    public void deleteAnnouncement(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + announcementId));
        announcementRepository.delete(announcement);
    }
}
