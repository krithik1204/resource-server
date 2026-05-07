package com.college.resourceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.resourceserver.dto.AssignRoleRequest;
import com.college.resourceserver.entities.User;
import com.college.resourceserver.service.UserService;



@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsersWithOutRole() {
	
	return	userService.findUsersWithoutRoles();
	
	}
	
	@PostMapping(value="/users/{id}/role",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<User> assignUserRole(@PathVariable Long id,@RequestBody AssignRoleRequest role) {
		
		System.out.println("Role is >>>"+role);
	System.out.println("Role is id>>>"+role.roleId());
	System.out.println("User is id>>>"+id);
	userService.assignRole(Long.valueOf(id), role.roleId());
	
	return	userService.findUsersWithoutRoles();
	
	}
	
}
