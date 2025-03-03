package com.vougue.tontineApp.repository;

import com.vougue.tontineApp.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
