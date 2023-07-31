package com.example.idvlabsTask.authentication.repository;

import com.example.idvlabsTask.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByAdminId(Long id);

    boolean existsByUsername(String username);
}