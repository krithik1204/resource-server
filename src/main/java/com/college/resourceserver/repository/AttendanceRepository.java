package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    
}
