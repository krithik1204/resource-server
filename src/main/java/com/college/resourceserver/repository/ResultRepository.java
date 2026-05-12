package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Result;
import com.college.resourceserver.entities.Student;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudent(Student student);
    List<Result> findByStudentId(Long studentId);
}
