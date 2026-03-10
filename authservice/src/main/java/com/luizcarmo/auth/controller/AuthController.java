package com.luizcarmo.auth.controller;

import com.luizcarmo.auth.dto.*;
import com.luizcarmo.auth.dto.request.LoginRequest;
import com.luizcarmo.auth.dto.request.RegisterRequest;
import com.luizcarmo.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Exposição dos endpoints públicos de autenticação.
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    // Endpoint para criação de usuário.

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    // Endpoint para geração de token JWT.
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}