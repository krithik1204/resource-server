package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    
}
