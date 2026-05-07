package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
