package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponse {
    
    private Long id;
    private String student;
    private Long studentId;
    private String course;
    private Long courseId;
    private LocalDate date;
    private String status;
}
