package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyResponse {
    
    private Long id;
    private UserResponse user;
    private String department;
    private String designation;
}
