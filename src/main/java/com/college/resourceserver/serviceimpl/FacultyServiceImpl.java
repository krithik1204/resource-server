package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.FacultyCreateRequest;
import com.college.resourceserver.dto.FacultyResponse;
import com.college.resourceserver.entities.Faculty;
import com.college.resourceserver.entities.User;
import com.college.resourceserver.entities.Department;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.FacultyRepository;
import com.college.resourceserver.repository.UserRepository;
import com.college.resourceserver.repository.DepartmentRepository;
import com.college.resourceserver.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {
    
    @Autowired
    FacultyRepository facultyRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<FacultyResponse> getAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        return entityDtoMapper.toFacultyResponseList(faculties);
    }
    
    @Override
    public FacultyResponse getFacultyById(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + facultyId));
        return entityDtoMapper.toFacultyResponse(faculty);
    }
    
    @Override
    public FacultyResponse createFaculty(FacultyCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        
        Faculty faculty = new Faculty();
        faculty.setUser(user);
        faculty.setDepartment(department);
        faculty.setDesignation(request.getDesignation());
        
        Faculty savedFaculty = facultyRepository.save(faculty);
        return entityDtoMapper.toFacultyResponse(savedFaculty);
    }
    
    @Override
    public FacultyResponse updateFaculty(Long facultyId, FacultyCreateRequest request) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + facultyId));
        
        if (request.getDesignation() != null) {
            faculty.setDesignation(request.getDesignation());
        }
        
        Faculty updatedFaculty = facultyRepository.save(faculty);
        return entityDtoMapper.toFacultyResponse(updatedFaculty);
    }
    
    @Override
    public void deleteFaculty(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + facultyId));
        facultyRepository.delete(faculty);
    }
}
