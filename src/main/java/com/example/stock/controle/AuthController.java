package com.example.stock.controle;

import com.example.stock.domain.User;
import com.example.stock.dto.AuthenticationDTO;
import com.example.stock.dto.LoginResponseDTO;
import com.example.stock.repository.UserRepository;
import com.example.stock.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.email(), data.senha())
            );

            User usuario = repository.findByLogin(data.email());
            if (usuario != null && new BCryptPasswordEncoder().matches(data.senha(), usuario.getSenha())) {
                String token = tokenService.generateToken(usuario);
                return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), token));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
