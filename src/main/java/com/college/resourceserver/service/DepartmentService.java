package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.DepartmentCreateRequest;
import com.college.resourceserver.dto.DepartmentResponse;

public interface DepartmentService {
    
    List<DepartmentResponse> getAllDepartments();
    
    DepartmentResponse getDepartmentById(Long departmentId);
    
    DepartmentResponse createDepartment(DepartmentCreateRequest request);
    
    DepartmentResponse updateDepartment(Long departmentId, DepartmentCreateRequest request);
    
    void deleteDepartment(Long departmentId);
}
