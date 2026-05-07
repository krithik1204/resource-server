package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
