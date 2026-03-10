package com.luizcarmo.auth.service;

import com.luizcarmo.auth.dto.*;
import com.luizcarmo.auth.dto.request.LoginRequest;
import com.luizcarmo.auth.dto.request.RegisterRequest;
import com.luizcarmo.auth.entity.User;
import com.luizcarmo.auth.exception.AuthException;
import com.luizcarmo.auth.repository.UserRepository;
import com.luizcarmo.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Implementa regras de registro e autenticação de usuários.
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    // Cria usuário, aplica hash na senha e retorna token JWT.
    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        repository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    // Valida credenciais e gera novo token JWT.
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}