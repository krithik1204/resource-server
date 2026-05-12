package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.AttendanceCreateRequest;
import com.college.resourceserver.dto.AttendanceResponse;

public interface AttendanceService {
    
    List<AttendanceResponse> getAllAttendances();
    
    AttendanceResponse getAttendanceById(Long attendanceId);
    
    AttendanceResponse createAttendance(AttendanceCreateRequest request);
    
    AttendanceResponse updateAttendance(Long attendanceId, AttendanceCreateRequest request);
    
    void deleteAttendance(Long attendanceId);
    
    List<AttendanceResponse> getAttendanceByStudentId(Long studentId);
}
