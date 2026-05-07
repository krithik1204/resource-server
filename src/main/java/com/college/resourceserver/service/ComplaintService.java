package com.college.resourceserver.service;

import java.util.List;
import com.college.resourceserver.dto.ComplaintCreateRequest;
import com.college.resourceserver.dto.ComplaintResponse;

public interface ComplaintService {
    
    List<ComplaintResponse> getAllComplaints();
    
    ComplaintResponse getComplaintById(Long complaintId);
    
    ComplaintResponse createComplaint(ComplaintCreateRequest request);
    
    ComplaintResponse updateComplaint(Long complaintId, ComplaintCreateRequest request);
    
    void deleteComplaint(Long complaintId);
}
