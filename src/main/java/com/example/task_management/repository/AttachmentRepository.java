package com.example.task_management.repository;

import com.example.task_management.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    // Poți adăuga metode personalizate aici dacă este nevoie, de exemplu:
    // List<Attachment> findByTaskId(Long taskId);
}
