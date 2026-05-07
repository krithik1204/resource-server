package com.college.resourceserver.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.resourceserver.entities.Role;
import com.college.resourceserver.repository.RoleRepository;
import com.college.resourceserver.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		
		return roleRepository.findAll();
	}

	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		System.out.println(role.getName());
		return roleRepository.save(role);
	}

		
	

}
