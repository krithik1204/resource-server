package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.FacultyCreateRequest;
import com.college.resourceserver.dto.FacultyResponse;

public interface FacultyService {
    
    List<FacultyResponse> getAllFaculties();
    
    FacultyResponse getFacultyById(Long facultyId);
    
    FacultyResponse createFaculty(FacultyCreateRequest request);
    
    FacultyResponse updateFaculty(Long facultyId, FacultyCreateRequest request);
    
    void deleteFaculty(Long facultyId);
}
