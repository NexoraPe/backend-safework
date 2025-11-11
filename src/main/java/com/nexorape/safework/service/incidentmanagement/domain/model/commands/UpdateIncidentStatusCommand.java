package com.nexorape.safework.service.incidentmanagement.domain.model.commands;

import com.nexorape.safework.service.incidentmanagement.domain.model.valueobjects.incident.IncidentStatus;

public record UpdateIncidentStatusCommand(Long incidentId,
                                          IncidentStatus status) {
}
