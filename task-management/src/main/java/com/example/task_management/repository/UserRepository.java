package com.example.task_management.repository;

import com.example.task_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Poți adăuga metode personalizate aici dacă este nevoie, de exemplu:
    // Optional<User> findByUsername(String username);
}
