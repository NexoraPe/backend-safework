package com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.incident;

public record CreateIncidentResource(
        Long userId,
        Long companyId,
        String title,
        String description,
        String location) {
}
