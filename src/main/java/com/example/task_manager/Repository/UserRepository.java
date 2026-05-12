package com.example.task_manager.Repository;

import com.example.task_manager.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method to find user by username
    Optional<User> findByUsername(String username);
}
