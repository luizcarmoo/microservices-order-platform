package com.luizcarmo.auth.exception;

// Exceção específica do domínio de autenticação.
// Usada para erros previsíveis como credenciais inválidas.
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

}