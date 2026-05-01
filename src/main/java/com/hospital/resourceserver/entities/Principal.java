package com.hospital.resourceserver.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "principal")
public class Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}