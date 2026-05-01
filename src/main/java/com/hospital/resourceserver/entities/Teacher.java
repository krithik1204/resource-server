package com.hospital.resourceserver.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String subject;
    private int experience;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}