package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.AnnouncementCreateRequest;
import com.college.resourceserver.dto.AnnouncementResponse;

public interface AnnouncementService {
    
    List<AnnouncementResponse> getAllAnnouncements();
    
    AnnouncementResponse getAnnouncementById(Long announcementId);
    
    AnnouncementResponse createAnnouncement(AnnouncementCreateRequest request);
    
    AnnouncementResponse updateAnnouncement(Long announcementId, AnnouncementCreateRequest request);
    
    void deleteAnnouncement(Long announcementId);
}
