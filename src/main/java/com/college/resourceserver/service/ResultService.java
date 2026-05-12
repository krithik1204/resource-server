package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.ResultCreateRequest;
import com.college.resourceserver.dto.ResultResponse;

public interface ResultService {
    
    List<ResultResponse> getAllResults();
    
    ResultResponse getResultById(Long resultId);
    
    ResultResponse createResult(ResultCreateRequest request);
    
    ResultResponse updateResult(Long resultId, ResultCreateRequest request);
    
    void deleteResult(Long resultId);
    
    List<ResultResponse> getResultsByStudentId(Long studentId);
}
