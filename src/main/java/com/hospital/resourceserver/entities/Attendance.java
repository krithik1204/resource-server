package com.hospital.resourceserver.entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}