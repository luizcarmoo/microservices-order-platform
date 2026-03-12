package com.luizcarmo.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    // Retorno da autenticação contendo o JWT.
    private String token;
}