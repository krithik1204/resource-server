package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    
    private Long id;
    private String title;
    private String code;
    private String department;
    private String faculty;
    private Long departmentId;
    private Long facultyId;
}
