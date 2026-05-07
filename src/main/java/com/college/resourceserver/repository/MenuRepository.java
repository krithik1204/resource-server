package com.college.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.resourceserver.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    
}
