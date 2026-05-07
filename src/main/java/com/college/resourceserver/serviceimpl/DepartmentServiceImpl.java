package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.DepartmentCreateRequest;
import com.college.resourceserver.dto.DepartmentResponse;
import com.college.resourceserver.entities.Department;
import com.college.resourceserver.entities.Faculty;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.DepartmentRepository;
import com.college.resourceserver.repository.FacultyRepository;
import com.college.resourceserver.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    FacultyRepository facultyRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return entityDtoMapper.toDepartmentResponseList(departments);
    }
    
    @Override
    public DepartmentResponse getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
        return entityDtoMapper.toDepartmentResponse(department);
    }
    
    @Override
    public DepartmentResponse createDepartment(DepartmentCreateRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        
        if (request.getHodId() != null) {
            Faculty hod = facultyRepository.findById(request.getHodId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            department.setHod(hod);
        }
        
        Department savedDepartment = departmentRepository.save(department);
        return entityDtoMapper.toDepartmentResponse(savedDepartment);
    }
    
    @Override
    public DepartmentResponse updateDepartment(Long departmentId, DepartmentCreateRequest request) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
        
        if (request.getName() != null) {
            department.setName(request.getName());
        }
        if (request.getHodId() != null) {
            Faculty hod = facultyRepository.findById(request.getHodId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            department.setHod(hod);
        }
        
        Department updatedDepartment = departmentRepository.save(department);
        return entityDtoMapper.toDepartmentResponse(updatedDepartment);
    }
    
    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
        departmentRepository.delete(department);
    }
}
