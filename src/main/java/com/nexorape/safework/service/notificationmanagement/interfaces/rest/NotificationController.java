package com.nexorape.safework.service.notificationmanagement.interfaces.rest;

import com.nexorape.safework.service.notificationmanagement.application.internal.queryservices.NotificationResponse;
import com.nexorape.safework.service.notificationmanagement.application.internal.transform.NotificationAssembler;
import com.nexorape.safework.service.notificationmanagement.infrastructure.persistence.jpa.repositories.NotificationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications", description = "Notification Management Endpoints")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final NotificationAssembler notificationAssembler;

    public NotificationController(NotificationRepository notificationRepository,
            NotificationAssembler notificationAssembler) {
        this.notificationRepository = notificationRepository;
        this.notificationAssembler = notificationAssembler;
    }

     /**
     * Returns all notifications belonging to a specific user, sorted by creation time.
     *
     * @param userId ID of the authenticated user requesting their notifications.
     * @return List of NotificationResponse objects.
     */

    @GetMapping("/my-notifications")
    @Operation(summary = "Get My Notifications")
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(@RequestParam String userId) {
        var notifications = notificationRepository.findByRecipientIdOrderByCreatedAtDesc(userId);
        var resources = notifications.stream()
                .map(notificationAssembler::toResponseFrom)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
