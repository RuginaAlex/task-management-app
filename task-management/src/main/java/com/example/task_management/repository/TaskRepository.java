package com.example.task_management.repository;

import com.example.task_management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Poți adăuga metode personalizate aici dacă este nevoie, de exemplu:
    // List<Task> findByUserId(Long userId);
}
