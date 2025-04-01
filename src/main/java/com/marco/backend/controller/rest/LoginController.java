package com.marco.backend.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.marco.backend.config.JwtTokenUtil;
import com.marco.backend.dto.auth.LoginDTO;
import com.marco.backend.dto.auth.RegistrarDTO;
import com.marco.backend.dto.auth.UserDTO;
import com.marco.backend.model.auth.User;
import com.marco.backend.service.auth.LoginService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")
public class LoginController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenUtil jUtil;

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(autenticar(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar (@RequestBody RegistrarDTO regDTO) {
        return loginService.registrar(regDTO);
    }
    
    private UserDTO autenticar(LoginDTO loginDTO){
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getLogin(),
                loginDTO.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails user = loginService.loadUserByUsername(loginDTO.getLogin());
        String token = jUtil.generateToken(user);
        User u = (User) user;
        UserDTO userDTO = new UserDTO(u);
        userDTO.setToken(token);
        return userDTO;
    }
}
