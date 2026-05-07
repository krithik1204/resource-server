package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.ComplaintCreateRequest;
import com.college.resourceserver.dto.ComplaintResponse;
import com.college.resourceserver.entities.Complaint;
import com.college.resourceserver.entities.Student;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.ComplaintRepository;
import com.college.resourceserver.repository.StudentRepository;
import com.college.resourceserver.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    
    @Autowired
    ComplaintRepository complaintRepository;
    
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<ComplaintResponse> getAllComplaints() {
        List<Complaint> complaints = complaintRepository.findAll();
        return entityDtoMapper.toComplaintResponseList(complaints);
    }
    
    @Override
    public ComplaintResponse getComplaintById(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
        return entityDtoMapper.toComplaintResponse(complaint);
    }
    
    @Override
    public ComplaintResponse createComplaint(ComplaintCreateRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        Complaint complaint = entityDtoMapper.toComplaintEntity(request);
        complaint.setStudent(student);
        
        Complaint savedComplaint = complaintRepository.save(complaint);
        return entityDtoMapper.toComplaintResponse(savedComplaint);
    }
    
    @Override
    public ComplaintResponse updateComplaint(Long complaintId, ComplaintCreateRequest request) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
        
        if (request.getMessage() != null) {
            complaint.setMessage(request.getMessage());
        }
        if (request.getStatus() != null) {
            complaint.setStatus(Complaint.Status.valueOf(request.getStatus()));
        }
        
        Complaint updatedComplaint = complaintRepository.save(complaint);
        return entityDtoMapper.toComplaintResponse(updatedComplaint);
    }
    
    @Override
    public void deleteComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
        complaintRepository.delete(complaint);
    }
}
