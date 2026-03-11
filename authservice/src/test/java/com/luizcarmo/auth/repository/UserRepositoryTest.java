package com.luizcarmo.auth.repository;

import com.luizcarmo.auth.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// Teste da camada de persistência utilizando JPA + banco em memória.
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Deve salvar usuário no banco")
    void shouldSaveUser() {

        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("123456");

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve buscar usuário pelo email")
    void shouldFindUserByEmail() {

        User user = new User();
        user.setEmail("user@email.com");
        user.setPassword("123456");

        userRepository.save(user);

        Optional<User> result = userRepository.findByEmail("user@email.com");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("user@email.com");
    }
}