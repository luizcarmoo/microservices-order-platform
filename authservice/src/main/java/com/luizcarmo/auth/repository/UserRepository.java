package com.luizcarmo.auth.repository;

import com.luizcarmo.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Consulta por email usada no fluxo de login.
    Optional<User> findByEmail(String email);

}