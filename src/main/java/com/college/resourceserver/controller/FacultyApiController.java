package com.college.resourceserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.college.resourceserver.dto.FacultyCreateRequest;
import com.college.resourceserver.dto.FacultyResponse;
import com.college.resourceserver.service.FacultyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/faculties")
public class FacultyApiController {
    
    @Autowired
    FacultyService facultyService;
    
    @GetMapping
    public ResponseEntity<List<FacultyResponse>> getAllFaculties() {
        List<FacultyResponse> faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FacultyResponse> getFacultyById(@PathVariable Long id) {
        FacultyResponse faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }
    
    @PostMapping
    public ResponseEntity<FacultyResponse> createFaculty(@Valid @RequestBody FacultyCreateRequest request) {
        FacultyResponse faculty = facultyService.createFaculty(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(faculty);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FacultyResponse> updateFaculty(@PathVariable Long id, @Valid @RequestBody FacultyCreateRequest request) {
        FacultyResponse faculty = facultyService.updateFaculty(id, request);
        return ResponseEntity.ok(faculty);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
