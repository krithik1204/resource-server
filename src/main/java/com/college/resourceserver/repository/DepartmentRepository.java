package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
}
