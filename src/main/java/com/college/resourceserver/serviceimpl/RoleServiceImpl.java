package com.college.resourceserver.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.resourceserver.dto.RoleCreateRequest;
import com.college.resourceserver.dto.RoleResponse;
import com.college.resourceserver.entities.Role;
import com.college.resourceserver.mapper.EntityDtoMapper;
import com.college.resourceserver.repository.RoleRepository;
import com.college.resourceserver.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EntityDtoMapper entityDtoMapper;

	@Override
	public List<RoleResponse> getRoles() {
		List<Role> roles = roleRepository.findAll();
		return entityDtoMapper.toRoleResponseList(roles);
	}

	@Override
	public RoleResponse addRole(RoleCreateRequest request) {
		Role role = entityDtoMapper.toRoleEntity(request);
		Role savedRole = roleRepository.save(role);
		return entityDtoMapper.toRoleResponse(savedRole);
	}

	@Override
	public RoleResponse getRoleById(Long roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
		return entityDtoMapper.toRoleResponse(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
		roleRepository.delete(role);
	}

}
