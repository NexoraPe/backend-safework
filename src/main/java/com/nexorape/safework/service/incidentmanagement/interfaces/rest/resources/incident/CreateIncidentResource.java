package com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.incident;

public record CreateIncidentResource(
                String title,
                String description,
                String location) {
}
