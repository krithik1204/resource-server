package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementResponse {
    
    private Long id;
    private String title;
    private String message;
    private String createdByRole;
    private LocalDateTime createdAt;
}
