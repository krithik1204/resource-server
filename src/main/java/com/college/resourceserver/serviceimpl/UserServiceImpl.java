package com.college.resourceserver.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.resourceserver.entities.Role;
import com.college.resourceserver.entities.User;
import com.college.resourceserver.repository.RoleRepository;
import com.college.resourceserver.repository.UserRepository;
import com.college.resourceserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<User> findUsersWithoutRoles() {
		// TODO Auto-generated method stub
		return userRepository.findUsersWithoutRoles();
	}

	@Override
	public void assignRole(Long userId, Long roleId) {
		// TODO Auto-generated method stub
		System.out.println("user role>>"+userId);
		System.out.println("Role role>>"+roleId);
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Role role = roleRepository.findById(Long.valueOf(roleId))
	            .orElseThrow(() -> new RuntimeException("Role not found"));
	    
	    user.getRoles().add(role);
	    userRepository.save(user);
	}

}
