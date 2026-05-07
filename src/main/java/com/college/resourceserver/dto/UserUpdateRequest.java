package com.college.resourceserver.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    
    private String name;
    
    @Email(message = "Email should be valid")
    private String email;
    
    private String password;
    
    private String phone_number;
    
    private LocalDate date_of_birth;
}
