package com.college.resourceserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;
@OneToOne
@JoinColumn(name = "hod_id")
private Faculty hod;
}