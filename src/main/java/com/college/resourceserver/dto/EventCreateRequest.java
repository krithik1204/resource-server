package com.college.resourceserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateRequest {
    
    @NotBlank(message = "Event name is required")
    private String name;
    
    private String description;
    
    private String status; // PENDING, APPROVED, REJECTED
    
    private Long createdByUserId;
}
