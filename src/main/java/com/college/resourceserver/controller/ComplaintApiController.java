package com.college.resourceserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.college.resourceserver.dto.ComplaintCreateRequest;
import com.college.resourceserver.dto.ComplaintResponse;
import com.college.resourceserver.service.ComplaintService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintApiController {
    
    @Autowired
    ComplaintService complaintService;
    
    @GetMapping
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        List<ComplaintResponse> complaints = complaintService.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(@PathVariable Long id) {
        ComplaintResponse complaint = complaintService.getComplaintById(id);
        return ResponseEntity.ok(complaint);
    }
    
    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(@Valid @RequestBody ComplaintCreateRequest request) {
        ComplaintResponse complaint = complaintService.createComplaint(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(complaint);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ComplaintResponse> updateComplaint(@PathVariable Long id, @Valid @RequestBody ComplaintCreateRequest request) {
        ComplaintResponse complaint = complaintService.updateComplaint(id, request);
        return ResponseEntity.ok(complaint);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.noContent().build();
    }
}
