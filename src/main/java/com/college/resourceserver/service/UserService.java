package com.college.resourceserver.service;

import java.util.List;

import com.college.resourceserver.entities.User;

public interface UserService {

	List<User> findUsersWithoutRoles();

	

	void assignRole(Long user, Long role);

}
