package com.college.resourceserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.college.resourceserver.dto.StudentCreateRequest;
import com.college.resourceserver.dto.StudentResponse;
import com.college.resourceserver.dto.AttendanceResponse;
import com.college.resourceserver.dto.ResultResponse;
import com.college.resourceserver.service.StudentService;
import com.college.resourceserver.service.AttendanceService;
import com.college.resourceserver.service.ResultService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentsApiController {
    
    @Autowired
    StudentService studentService;
    
    @Autowired
    AttendanceService attendanceService;
    
    @Autowired
    ResultService resultService;
    
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        StudentResponse student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        StudentResponse student = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentCreateRequest request) {
        StudentResponse student = studentService.updateStudent(id, request);
        return ResponseEntity.ok(student);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Get attendance records for a student
     */
    @GetMapping("/{studentId}/attendance")
    public ResponseEntity<List<AttendanceResponse>> getStudentAttendance(@PathVariable Long studentId) {
        List<AttendanceResponse> attendances = attendanceService.getAttendanceByStudentId(studentId);
        return ResponseEntity.ok(attendances);
    }
    
    /**
     * Get exam results/transcript for a student
     */
    @GetMapping("/{studentId}/results")
    public ResponseEntity<List<ResultResponse>> getStudentResults(@PathVariable Long studentId) {
        List<ResultResponse> results = resultService.getResultsByStudentId(studentId);
        return ResponseEntity.ok(results);
    }
}

