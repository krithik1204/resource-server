package com.college.resourceserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@OneToOne
@JoinColumn(name = "user_id")
private User user;
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;

private String designation;}