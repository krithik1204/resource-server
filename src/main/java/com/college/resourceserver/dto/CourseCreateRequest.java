package com.college.resourceserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {
    
    @NotBlank(message = "Course title is required")
    private String title;
    
    @NotBlank(message = "Course code is required")
    private String code;
    
    @NotNull(message = "Department ID is required")
    private Long departmentId;
    
    @NotNull(message = "Faculty ID is required")
    private Long facultyId;
}
