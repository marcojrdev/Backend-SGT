package com.marco.backend.repository.auth;

import java.util.Optional;

import com.marco.backend.model.auth.User;
import com.marco.backend.repository.base.GenericRepository;

public interface UsuarioRepository extends GenericRepository<User, Long>{
    
    Optional<User> findByLogin(String login);

}
