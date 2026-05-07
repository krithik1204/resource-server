package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.StudentCreateRequest;
import com.college.resourceserver.dto.StudentResponse;

public interface StudentService {
    
    List<StudentResponse> getAllStudents();
    
    StudentResponse getStudentById(Long studentId);
    
    StudentResponse createStudent(StudentCreateRequest request);
    
    StudentResponse updateStudent(Long studentId, StudentCreateRequest request);
    
    void deleteStudent(Long studentId);
}
