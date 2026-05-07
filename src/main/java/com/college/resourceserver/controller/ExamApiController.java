package com.college.resourceserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.college.resourceserver.dto.ExamCreateRequest;
import com.college.resourceserver.dto.ExamResponse;
import com.college.resourceserver.service.ExamService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/exams")
public class ExamApiController {
    
    @Autowired
    ExamService examService;
    
    @GetMapping
    public ResponseEntity<List<ExamResponse>> getAllExams() {
        List<ExamResponse> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExamResponse> getExamById(@PathVariable Long id) {
        ExamResponse exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }
    
    @PostMapping
    public ResponseEntity<ExamResponse> createExam(@Valid @RequestBody ExamCreateRequest request) {
        ExamResponse exam = examService.createExam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(exam);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExamResponse> updateExam(@PathVariable Long id, @Valid @RequestBody ExamCreateRequest request) {
        ExamResponse exam = examService.updateExam(id, request);
        return ResponseEntity.ok(exam);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
