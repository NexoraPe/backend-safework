package com.nexorape.safework.service.notificationmanagement.application.internal.queryservices;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponse(
        UUID id,
        String subject,
        String body,
        LocalDateTime createdAt,
        boolean isRead) {
}
