package com.college.resourceserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Roll number is required")
    private String rollNumber;
    
    @NotNull(message = "Department ID is required")
    private Long departmentId;
    
    private Integer year;
    
    private Double attendancePercentage;
    
    private Double cgpa;
}
