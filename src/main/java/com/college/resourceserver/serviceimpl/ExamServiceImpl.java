package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.ExamCreateRequest;
import com.college.resourceserver.dto.ExamResponse;
import com.college.resourceserver.entities.Exam;
import com.college.resourceserver.entities.Course;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.ExamRepository;
import com.college.resourceserver.repository.CourseRepository;
import com.college.resourceserver.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {
    
    @Autowired
    ExamRepository examRepository;
    
    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<ExamResponse> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        return entityDtoMapper.toExamResponseList(exams);
    }
    
    @Override
    public ExamResponse getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
        return entityDtoMapper.toExamResponse(exam);
    }
    
    @Override
    public ExamResponse createExam(ExamCreateRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        
        Exam exam = new Exam();
        exam.setName(request.getName());
        exam.setExamDate(request.getExamDate());
        exam.setCourse(course);
        
        Exam savedExam = examRepository.save(exam);
        return entityDtoMapper.toExamResponse(savedExam);
    }
    
    @Override
    public ExamResponse updateExam(Long examId, ExamCreateRequest request) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
        
        if (request.getName() != null) {
            exam.setName(request.getName());
        }
        if (request.getExamDate() != null) {
            exam.setExamDate(request.getExamDate());
        }
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            exam.setCourse(course);
        }
        
        Exam updatedExam = examRepository.save(exam);
        return entityDtoMapper.toExamResponse(updatedExam);
    }
    
    @Override
    public void deleteExam(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
        examRepository.delete(exam);
    }
}
