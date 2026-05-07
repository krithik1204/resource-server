package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.StudentCreateRequest;
import com.college.resourceserver.dto.StudentResponse;
import com.college.resourceserver.entities.Student;
import com.college.resourceserver.entities.User;
import com.college.resourceserver.entities.Department;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.StudentRepository;
import com.college.resourceserver.repository.UserRepository;
import com.college.resourceserver.repository.DepartmentRepository;
import com.college.resourceserver.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return entityDtoMapper.toStudentResponseList(students);
    }
    
    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        return entityDtoMapper.toStudentResponse(student);
    }
    
    @Override
    public StudentResponse createStudent(StudentCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        
        Student student = new Student();
        student.setUser(user);
        student.setRollNumber(request.getRollNumber());
        student.setDepartment(department);
        student.setYear(request.getYear());
        student.setAttendancePercentage(request.getAttendancePercentage());
        student.setCgpa(request.getCgpa());
        
        Student savedStudent = studentRepository.save(student);
        return entityDtoMapper.toStudentResponse(savedStudent);
    }
    
    @Override
    public StudentResponse updateStudent(Long studentId, StudentCreateRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        
        if (request.getRollNumber() != null) {
            student.setRollNumber(request.getRollNumber());
        }
        if (request.getYear() != null) {
            student.setYear(request.getYear());
        }
        if (request.getAttendancePercentage() != null) {
            student.setAttendancePercentage(request.getAttendancePercentage());
        }
        if (request.getCgpa() != null) {
            student.setCgpa(request.getCgpa());
        }
        
        Student updatedStudent = studentRepository.save(student);
        return entityDtoMapper.toStudentResponse(updatedStudent);
    }
    
    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        studentRepository.delete(student);
    }
}
