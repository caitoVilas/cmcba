package com.cmcba.domain.repositories;

import com.cmcba.domain.entities.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@Repository
public interface UserRepository extends JpaRepository<UserApplication, Long> {
    Optional<UserApplication> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
