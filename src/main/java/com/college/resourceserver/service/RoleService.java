package com.college.resourceserver.service;

import java.util.List;

import com.college.resourceserver.dto.RoleCreateRequest;
import com.college.resourceserver.dto.RoleResponse;

public interface RoleService {

	List<RoleResponse> getRoles();

	RoleResponse addRole(RoleCreateRequest request);

	RoleResponse getRoleById(Long roleId);

	void deleteRole(Long roleId);

}
