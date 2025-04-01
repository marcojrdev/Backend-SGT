package com.marco.backend.dto.auth;

import java.io.Serializable;

import com.marco.backend.model.auth.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrarDTO implements Serializable{
    
    private String nome;
    private String login;
    private String password;
    private String sobrenome;

    public RegistrarDTO() {}

    public RegistrarDTO(User user) {
        this.nome = user.getNome();
        this.login = user.getLogin();
        this.sobrenome = user.getSobrenome();
    }
    
}
