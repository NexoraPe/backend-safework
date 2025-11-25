package com.nexorape.safework.service.notificationmanagement.infrastructure.persistence.jpa.repositories;

import com.nexorape.safework.service.notificationmanagement.domain.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    java.util.List<Notification> findByRecipientIdOrderByCreatedAtDesc(String recipientId);
}
