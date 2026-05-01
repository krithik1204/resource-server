package com.hospital.resourceserver.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private int marksObtained;
    private int totalMarks;
    private String examType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}