package com.ecotrack.backend.repository;

import com.ecotrack.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    // Finds a user by email and password for login validation
    Optional<User> findByEmailAndPassword(String email, String password);
}
