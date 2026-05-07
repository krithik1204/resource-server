package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    
    private Long id;
    private UserResponse user;
    private String rollNumber;
    private String department;
    private Integer year;
    private Double attendancePercentage;
    private Double cgpa;
}
