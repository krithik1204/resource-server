package com.college.resourceserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.college.resourceserver.dto.ResultCreateRequest;
import com.college.resourceserver.dto.ResultResponse;
import com.college.resourceserver.service.ResultService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/results")
public class ResultsApiController {
    
    @Autowired
    ResultService resultService;
    
    @GetMapping
    public ResponseEntity<List<ResultResponse>> getAllResults() {
        List<ResultResponse> results = resultService.getAllResults();
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getResultById(@PathVariable Long id) {
        ResultResponse result = resultService.getResultById(id);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping
    public ResponseEntity<ResultResponse> createResult(@Valid @RequestBody ResultCreateRequest request) {
        ResultResponse result = resultService.createResult(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updateResult(@PathVariable Long id, @Valid @RequestBody ResultCreateRequest request) {
        ResultResponse result = resultService.updateResult(id, request);
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
