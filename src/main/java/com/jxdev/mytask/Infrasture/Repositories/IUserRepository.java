package com.jxdev.mytask.Infrasture.Repositories;

import com.jxdev.mytask.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    // Buscar usuario por email (útil para autenticación y registro)
    Optional<User> findByEmail(String email);

    // Buscar usuario por nombre de usuario (username)
    Optional<User> findByUsername(String username);

    // Buscar usuario por email y contraseña (para autenticación básica)
    Optional<User> findByEmailAndPassword(String email, String password);
}
