package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
    
    private Long id;
    private String name;
    private LocalDateTime examDate;
    private String course;
    private Long courseId;
}
