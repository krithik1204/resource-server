package com.hospital.resourceserver.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;
}