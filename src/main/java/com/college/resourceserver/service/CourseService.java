package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.CourseCreateRequest;
import com.college.resourceserver.dto.CourseResponse;

public interface CourseService {
    
    List<CourseResponse> getAllCourses();
    
    CourseResponse getCourseById(Long courseId);
    
    CourseResponse createCourse(CourseCreateRequest request);
    
    CourseResponse updateCourse(Long courseId, CourseCreateRequest request);
    
    void deleteCourse(Long courseId);
}
