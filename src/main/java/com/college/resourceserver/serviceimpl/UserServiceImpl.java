package com.college.resourceserver.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.resourceserver.dto.UserCreateRequest;
import com.college.resourceserver.dto.UserResponse;
import com.college.resourceserver.dto.UserUpdateRequest;
import com.college.resourceserver.entities.Role;
import com.college.resourceserver.entities.User;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.RoleRepository;
import com.college.resourceserver.repository.UserRepository;
import com.college.resourceserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EntityDtoMapper entityDtoMapper;

	@Override
	public List<UserResponse> findUsersWithoutRoles() {
		List<User> users = userRepository.findUsersWithoutRoles();
		return entityDtoMapper.toUserResponseList(users);
	}

	@Override
	public UserResponse createUser(UserCreateRequest request) {
		User user = entityDtoMapper.toUserEntity(request);
		User savedUser = userRepository.save(user);
		return entityDtoMapper.toUserResponse(savedUser);
	}

	@Override
	public UserResponse updateUser(Long userId, UserUpdateRequest request) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		
		entityDtoMapper.updateUserEntity(request, user);
		User updatedUser = userRepository.save(user);
		return entityDtoMapper.toUserResponse(updatedUser);
	}

	@Override
	public UserResponse getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		return entityDtoMapper.toUserResponse(user);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		return entityDtoMapper.toUserResponseList(users);
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		userRepository.delete(user);
	}

	@Override
	public void assignRole(Long userId, Long roleId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Role role = roleRepository.findById(Long.valueOf(roleId))
	            .orElseThrow(() -> new RuntimeException("Role not found"));
	    
	    user.getRoles().add(role);
	    userRepository.save(user);
	}

}
