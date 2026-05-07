package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
