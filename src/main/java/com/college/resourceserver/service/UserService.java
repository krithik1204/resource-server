package com.college.resourceserver.service;

import java.util.List;

import com.college.resourceserver.dto.UserCreateRequest;
import com.college.resourceserver.dto.UserResponse;
import com.college.resourceserver.dto.UserUpdateRequest;

public interface UserService {

	List<UserResponse> findUsersWithoutRoles();

	UserResponse createUser(UserCreateRequest request);

	UserResponse updateUser(Long userId, UserUpdateRequest request);

	UserResponse getUserById(Long userId);

	List<UserResponse> getAllUsers();

	void deleteUser(Long userId);

	void assignRole(Long user, Long role);

}
