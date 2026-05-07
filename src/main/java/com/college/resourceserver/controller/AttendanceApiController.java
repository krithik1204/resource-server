package com.college.resourceserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.college.resourceserver.dto.AttendanceCreateRequest;
import com.college.resourceserver.dto.AttendanceResponse;
import com.college.resourceserver.service.AttendanceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceApiController {
    
    @Autowired
    AttendanceService attendanceService;
    
    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAllAttendances() {
        List<AttendanceResponse> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable Long id) {
        AttendanceResponse attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }
    
    @PostMapping
    public ResponseEntity<AttendanceResponse> createAttendance(@Valid @RequestBody AttendanceCreateRequest request) {
        AttendanceResponse attendance = attendanceService.createAttendance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateAttendance(@PathVariable Long id, @Valid @RequestBody AttendanceCreateRequest request) {
        AttendanceResponse attendance = attendanceService.updateAttendance(id, request);
        return ResponseEntity.ok(attendance);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
