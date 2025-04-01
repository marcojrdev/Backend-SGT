package com.marco.backend.service.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marco.backend.config.JwtTokenUtil;
import com.marco.backend.dto.auth.BearerToken;
import com.marco.backend.dto.auth.RegistrarDTO;
import com.marco.backend.dto.auth.UserDTO;
import com.marco.backend.exception.ValidacaoException;
import com.marco.backend.model.auth.User;
import com.marco.backend.repository.auth.RoleRepository;
import com.marco.backend.repository.auth.UsuarioRepository;
import com.marco.backend.repository.base.GenericRepository;
import com.marco.backend.service.base.ServiceGenerico;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginService extends ServiceGenerico<User, Long> implements UserDetailsService{

    @Autowired
    UsuarioRepository uRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    private JwtTokenUtil jUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = uRepo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não foi encontrado!"));
        return user;
    }

    public ResponseEntity<?> registrar(RegistrarDTO dto){
        if(uRepo.findByLogin(dto.getLogin()).isPresent()){
            return new ResponseEntity<>("Já existe um usuário com este login!", HttpStatus.SEE_OTHER);
        }
        else{
            User user = new User();
            user.setSobrenome(dto.getSobrenome());
            user.setNome(dto.getNome());
            user.setLogin(dto.getLogin());
            user.setSenha(new BCryptPasswordEncoder().encode(dto.getPassword()));
            user.setNivel(roleRepo.findById("ROLE_USER").get());
            user.setAtivado(true);
            uRepo.save(user);
            String token = jUtil.generateToken(user);
            UserDTO u = new UserDTO(user);
            u.setToken(token);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
    }

    public User alterarUsuario(Long id, RegistrarDTO dto){
        User user = uRepo.findById(id).orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
        user.setSobrenome(dto.getSobrenome());
        user.setNome(dto.getNome());
        user.setLogin(dto.getLogin());
        if(dto.getPassword() != null && !dto.getPassword().isBlank() && !dto.getPassword().isEmpty())
            user.setSenha(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setNivel(roleRepo.findById("ROLE_USER").get());
        user.setAtivado(true);
        uRepo.save(user);
        return user;
    }

    public static User getUser(){
        User u = null;
        try {
            u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ValidacaoException("Usuário não está logado!");
        }
        return u;
    }
    
    @Override
    public GenericRepository<User, Long> getRepository() {
        return uRepo;
    }

}
