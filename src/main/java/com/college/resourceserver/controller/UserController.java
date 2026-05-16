package com.college.resourceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.college.resourceserver.dto.AssignRoleRequest;
import com.college.resourceserver.dto.UserCreateRequest;
import com.college.resourceserver.dto.UserResponse;
import com.college.resourceserver.dto.UserUpdateRequest;
import com.college.resourceserver.projection.TeacherProjection;
import com.college.resourceserver.service.UserService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getAllUsersWithOutRole() {
		List<UserResponse> users = userService.findUsersWithoutRoles();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		UserResponse user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/users/all")
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		List<UserResponse> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
		UserResponse user = userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
		UserResponse user = userService.updateUser(id, request);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/users/{id}/role", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserResponse>> assignUserRole(@PathVariable Long id, @RequestBody AssignRoleRequest role) {
		userService.assignRole(id, role.roleId());
		List<UserResponse> users = userService.findUsersWithoutRoles();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value="/users/faculty", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TeacherProjection>> getTeachers() {
		System.out.println("from Teachers");
		List<TeacherProjection> list=	userService.getTeachers();
		return ResponseEntity.ok(list);
	}
	
}
