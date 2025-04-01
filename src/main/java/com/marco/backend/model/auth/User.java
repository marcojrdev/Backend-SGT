package com.marco.backend.model.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marco.backend.model.base.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User extends AbstractEntity implements UserDetails{
    
    private String login;

    private String nome;

    private String sobrenome;

    private String senha;

    private boolean ativado;

    @ManyToOne
    @JoinColumn(name = "role_name", nullable = false)
    private Role nivel;

    public User() {
    }

    /* ---------------------------------------------------------- */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.nivel);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativado;
    }

    /* -------------------------------------------------------- */

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

    public Role getNivel() {
        return nivel;
    }

    public void setNivel(Role nivel) {
        this.nivel = nivel;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    protected User map(AbstractEntity a) {
        User u = (User) a;
        this.login = u.getLogin();
        this.nome = u.getNome();
        this.sobrenome = u.getSobrenome();
        if(u.getSenha() != null && !u.getSenha().isEmpty())
            this.senha = new BCryptPasswordEncoder().encode(u.getSenha());
        this.nivel = u.getNivel();
        return this;
    }

}
