package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Attendance;
import com.college.resourceserver.entities.Student;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
    List<Attendance> findByStudentId(Long studentId);
}
