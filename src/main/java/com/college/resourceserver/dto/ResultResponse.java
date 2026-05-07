package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {
    
    private Long id;
    private String student;
    private Long studentId;
    private String course;
    private Long courseId;
    private Double marks;
    private String grade;
}
