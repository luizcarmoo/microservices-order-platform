package com.luizcarmo.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Credenciais usadas para autenticação.
@Data
public class LoginRequest {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}