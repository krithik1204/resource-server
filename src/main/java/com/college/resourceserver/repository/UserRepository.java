package com.college.resourceserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.college.resourceserver.entities.User;
import com.college.resourceserver.projection.TeacherProjection;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.roles IS EMPTY")
	List<User> findUsersWithoutRoles();

	
	@Query(value = """
		    SELECT u.id as id, u.name as name
		    FROM users u
		    JOIN user_roles ur ON u.id = ur.user_id
		    JOIN roles r ON ur.role_id = r.id
		    WHERE r.name = 'ROLE_TEACHER'
		    """, nativeQuery = true)
		List<TeacherProjection> getTeachers();
	
}
