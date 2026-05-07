package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    
    private Long id;
    private String name;
    private String description;
    private String status;
    private String createdByUser;
    private Long createdByUserId;
    private LocalDateTime createdAt;
}
