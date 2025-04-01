package com.marco.backend.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.backend.model.auth.Role;

public interface RoleRepository  extends JpaRepository<Role, String>{
    
}
