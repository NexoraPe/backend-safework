package com.nexorape.safework.service.incidentmanagement.domain.model.commands;

public record CreateIncidentCommand(
        String title,
        String description,
        String location) {
}
