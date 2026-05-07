package com.college.resourceserver.service;

import java.util.List;

import com.college.resourceserver.entities.Role;

public interface RoleService {

	public List<Role> getRoles();

	public Role addRole(Role role);

	
	
}
