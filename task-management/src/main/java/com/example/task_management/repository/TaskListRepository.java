package com.example.task_management.repository;

import com.example.task_management.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    // Poți adăuga metode personalizate aici dacă este nevoie, de exemplu:
    // List<TaskList> findByUserId(Long userId);
}
