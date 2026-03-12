package com.luizcarmo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Configuração de segurança da API.
// Arquitetura stateless baseada em JWT.
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                // API REST não mantém sessão no servidor.
                // Cada requisição deve trazer seu próprio token JWT.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // CSRF não é necessário em APIs stateless.
                // Mantemos desativado para permitir chamadas REST simples.
                .csrf(csrf -> csrf.disable())

                // H2 Console usa iframe para renderização.
                // É necessário liberar frameOptions para acesso no navegador.
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )

                // Regras de autorização da aplicação.
                .authorizeHttpRequests(auth -> auth

                        // Endpoints públicos de autenticação
                        .requestMatchers("/auth/**").permitAll()

                        // Liberação do console H2 apenas para ambiente de desenvolvimento
                        .requestMatchers("/h2-console/**").permitAll()

                        // Liberação de acesso ao actuator
                        .requestMatchers("/actuator/**").permitAll()

                        // Qualquer outro endpoint exige autenticação válida
                        .anyRequest().authenticated()
                )

                // API não utiliza formulário de login padrão do Spring Security.
                .formLogin(form -> form.disable())

                // Toda autenticação será via JWT.
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        // Gera hash com salt automaticamente.
        return new BCryptPasswordEncoder();
    }
}