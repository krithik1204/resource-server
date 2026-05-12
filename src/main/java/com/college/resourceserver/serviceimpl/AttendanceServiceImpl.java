package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.AttendanceCreateRequest;
import com.college.resourceserver.dto.AttendanceResponse;
import com.college.resourceserver.entities.Attendance;
import com.college.resourceserver.entities.Student;
import com.college.resourceserver.entities.Course;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.AttendanceRepository;
import com.college.resourceserver.repository.StudentRepository;
import com.college.resourceserver.repository.CourseRepository;
import com.college.resourceserver.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    
    @Autowired
    AttendanceRepository attendanceRepository;
    
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<AttendanceResponse> getAllAttendances() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return entityDtoMapper.toAttendanceResponseList(attendances);
    }
    
    @Override
    public AttendanceResponse getAttendanceById(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));
        return entityDtoMapper.toAttendanceResponse(attendance);
    }
    
    @Override
    public AttendanceResponse createAttendance(AttendanceCreateRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setDate(request.getDate());
        attendance.setStatus(Attendance.Status.valueOf(request.getStatus()));
        
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return entityDtoMapper.toAttendanceResponse(savedAttendance);
    }
    
    @Override
    public AttendanceResponse updateAttendance(Long attendanceId, AttendanceCreateRequest request) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));
        
        if (request.getStatus() != null) {
            attendance.setStatus(Attendance.Status.valueOf(request.getStatus()));
        }
        if (request.getDate() != null) {
            attendance.setDate(request.getDate());
        }
        
        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return entityDtoMapper.toAttendanceResponse(updatedAttendance);
    }
    
    @Override
    public void deleteAttendance(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));
        attendanceRepository.delete(attendance);
    }
    
    @Override
    public List<AttendanceResponse> getAttendanceByStudentId(Long studentId) {
        List<Attendance> attendances = attendanceRepository.findByStudentId(studentId);
        return entityDtoMapper.toAttendanceResponseList(attendances);
    }
}
