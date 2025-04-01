package com.marco.backend.dto.auth;

import com.marco.backend.model.auth.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String nome;
    private String sobreNome;
    private String login;
    private String token;

    public UserDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.sobreNome = user.getSobrenome();
        this.login = user.getLogin();
    }
}
