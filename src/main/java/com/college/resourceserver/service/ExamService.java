package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.ExamCreateRequest;
import com.college.resourceserver.dto.ExamResponse;

public interface ExamService {
    
    List<ExamResponse> getAllExams();
    
    ExamResponse getExamById(Long examId);
    
    ExamResponse createExam(ExamCreateRequest request);
    
    ExamResponse updateExam(Long examId, ExamCreateRequest request);
    
    void deleteExam(Long examId);
}
