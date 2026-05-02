package com.hospital.resourceserver.repository;

import com.hospital.resourceserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByIsActiveTrue();
    List<User> findAllByIsActiveFalse();
}
