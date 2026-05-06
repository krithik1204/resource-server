package com.college.resourceserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String title;
@Column(unique = true)
private String code;
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;

@ManyToOne
@JoinColumn(name = "faculty_id")
private Faculty faculty;
}