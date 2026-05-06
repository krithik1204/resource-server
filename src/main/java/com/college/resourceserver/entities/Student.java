package com.college.resourceserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(unique = true)
	private String rollNumber;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@Column(name = "\"YEAR\"")
	private Integer year;
	private Double attendancePercentage;
	private Double cgpa;
}