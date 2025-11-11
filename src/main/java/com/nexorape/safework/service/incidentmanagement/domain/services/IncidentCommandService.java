package com.nexorape.safework.service.incidentmanagement.domain.services;

import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.CreateIncidentCommand;

import java.util.Optional;

public interface IncidentCommandService {
    Optional<Incident> handle(CreateIncidentCommand createIncidentCommand);
}
