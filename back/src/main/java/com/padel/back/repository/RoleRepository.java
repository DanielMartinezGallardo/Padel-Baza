package com.padel.back.repository;

import com.padel.back.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> { 
    Optional<Role> findByNombre(String name);
}
