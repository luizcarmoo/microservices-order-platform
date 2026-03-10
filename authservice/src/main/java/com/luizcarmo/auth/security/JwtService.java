package com.luizcarmo.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

// Responsável por gerar tokens JWT usados na autenticação stateless.
@Service
public class JwtService {

    // Chave >= 256 bits exigida pelo algoritmo HS256.
    private static final String SECRET =
            "my-super-secret-key-for-jwt-authentication-2026";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}