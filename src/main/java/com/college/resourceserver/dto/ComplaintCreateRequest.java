package com.college.resourceserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintCreateRequest {
    
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    @NotBlank(message = "Message is required")
    private String message;
    
    private String status; // OPEN, RESOLVED
}
