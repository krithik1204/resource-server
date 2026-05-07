package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.CourseCreateRequest;
import com.college.resourceserver.dto.CourseResponse;
import com.college.resourceserver.entities.Course;
import com.college.resourceserver.entities.Department;
import com.college.resourceserver.entities.Faculty;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.CourseRepository;
import com.college.resourceserver.repository.DepartmentRepository;
import com.college.resourceserver.repository.FacultyRepository;
import com.college.resourceserver.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    FacultyRepository facultyRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return entityDtoMapper.toCourseResponseList(courses);
    }
    
    @Override
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        return entityDtoMapper.toCourseResponse(course);
    }
    
    @Override
    public CourseResponse createCourse(CourseCreateRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setCode(request.getCode());
        course.setDepartment(department);
        course.setFaculty(faculty);
        
        Course savedCourse = courseRepository.save(course);
        return entityDtoMapper.toCourseResponse(savedCourse);
    }
    
    @Override
    public CourseResponse updateCourse(Long courseId, CourseCreateRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        
        if (request.getTitle() != null) {
            course.setTitle(request.getTitle());
        }
        if (request.getCode() != null) {
            course.setCode(request.getCode());
        }
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            course.setDepartment(department);
        }
        if (request.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(request.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            course.setFaculty(faculty);
        }
        
        Course updatedCourse = courseRepository.save(course);
        return entityDtoMapper.toCourseResponse(updatedCourse);
    }
    
    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        courseRepository.delete(course);
    }
}
