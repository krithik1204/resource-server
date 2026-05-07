package com.college.resourceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.college.resourceserver.dto.RoleCreateRequest;
import com.college.resourceserver.dto.RoleResponse;
import com.college.resourceserver.service.RoleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RolesApiController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping(value="/roles")
	public ResponseEntity<List<RoleResponse>> getRoles() {
		List<RoleResponse> roles = roleService.getRoles();
		return ResponseEntity.ok(roles);
	}

	@GetMapping(value="/roles/{id}")
	public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
		RoleResponse role = roleService.getRoleById(id);
		return ResponseEntity.ok(role);
	}
	
	@PostMapping(value="/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody RoleCreateRequest request) {
		RoleResponse role = roleService.addRole(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(role);
	}

	@DeleteMapping(value="/roles/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return ResponseEntity.noContent().build();
	}
	
}

