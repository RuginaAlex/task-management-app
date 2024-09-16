package com.example.task_management.api;

import com.example.task_management.model.Attachment;
import com.example.task_management.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping
    public List<Attachment> getAllAttachments() {
        return attachmentService.getAllAttachments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attachment> getAttachmentById(@PathVariable Long id) {
        Optional<Attachment> attachment = attachmentService.getAttachmentById(id);
        return attachment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Attachment createAttachment(@RequestBody Attachment attachment) {
        return attachmentService.saveAttachment(attachment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attachment> updateAttachment(@PathVariable Long id, @RequestBody Attachment attachment) {
        Optional<Attachment> existingAttachment = attachmentService.getAttachmentById(id);
        if (existingAttachment.isPresent()) {
            attachment.setId(id);
            return ResponseEntity.ok(attachmentService.saveAttachment(attachment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        if (attachmentService.getAttachmentById(id).isPresent()) {
            attachmentService.deleteAttachment(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
