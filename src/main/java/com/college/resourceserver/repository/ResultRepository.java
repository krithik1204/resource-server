package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    
}
