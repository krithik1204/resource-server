package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintResponse {
    
    private Long id;
    private String student;
    private Long studentId;
    private String message;
    private String status;
    private LocalDateTime createdAt;
}
