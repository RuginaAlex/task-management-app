package com.example.task_management.repository;

import com.example.task_management.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Poți adăuga metode personalizate aici dacă este nevoie, de exemplu:
    // List<Comment> findByTaskId(Long taskId);
}
