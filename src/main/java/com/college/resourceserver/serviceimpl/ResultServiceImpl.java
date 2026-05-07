package com.college.resourceserver.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.resourceserver.dto.ResultCreateRequest;
import com.college.resourceserver.dto.ResultResponse;
import com.college.resourceserver.entities.Result;
import com.college.resourceserver.entities.Student;
import com.college.resourceserver.entities.Course;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.ResultRepository;
import com.college.resourceserver.repository.StudentRepository;
import com.college.resourceserver.repository.CourseRepository;
import com.college.resourceserver.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {
    
    @Autowired
    ResultRepository resultRepository;
    
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    EntityDtoMapper entityDtoMapper;
    
    @Override
    public List<ResultResponse> getAllResults() {
        List<Result> results = resultRepository.findAll();
        return entityDtoMapper.toResultResponseList(results);
    }
    
    @Override
    public ResultResponse getResultById(Long resultId) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + resultId));
        return entityDtoMapper.toResultResponse(result);
    }
    
    @Override
    public ResultResponse createResult(ResultCreateRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        
        Result result = new Result();
        result.setStudent(student);
        result.setCourse(course);
        result.setMarks(request.getMarks());
        result.setGrade(request.getGrade());
        
        Result savedResult = resultRepository.save(result);
        return entityDtoMapper.toResultResponse(savedResult);
    }
    
    @Override
    public ResultResponse updateResult(Long resultId, ResultCreateRequest request) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + resultId));
        
        if (request.getMarks() != null) {
            result.setMarks(request.getMarks());
        }
        if (request.getGrade() != null) {
            result.setGrade(request.getGrade());
        }
        
        Result updatedResult = resultRepository.save(result);
        return entityDtoMapper.toResultResponse(updatedResult);
    }
    
    @Override
    public void deleteResult(Long resultId) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + resultId));
        resultRepository.delete(result);
    }
}
