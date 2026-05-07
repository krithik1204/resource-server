package com.college.resourceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.resourceserver.entities.Role;
import com.college.resourceserver.entities.User;
import com.college.resourceserver.service.RoleService;

@RestController
@RequestMapping("/api")
public class RolesApiController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping(value="/roles")
	public List<Role> roles() {
		
		return roleService.getRoles();
		
	}
	
	@PostMapping(value="/roles")
	public Role roles(@RequestBody Role role) {
		
		return roleService.addRole(role);
		
	}
	
	
	
}
