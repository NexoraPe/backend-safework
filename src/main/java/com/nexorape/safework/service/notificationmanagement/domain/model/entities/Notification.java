package com.nexorape.safework.service.notificationmanagement.domain.model.entities;

import com.nexorape.safework.service.notificationmanagement.domain.model.valueobjects.NotificationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Notification entity representing a system-generated message sent to a user.
 * Stores basic metadata such as subject, body, status, and timestamps.
 */

@Entity
@Getter
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String recipientId;

    private String subject;

    private String body;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private LocalDateTime createdAt;

    public Notification(String recipientId, String subject, String body) {
        this.recipientId = recipientId;
        this.subject = subject;
        this.body = body;
        this.status = NotificationStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
    }
}
