package com.nexorape.safework.service.incidentmanagement.application.internal.commandservices;

import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.CreateIncidentCommand;
import com.nexorape.safework.service.incidentmanagement.domain.services.IncidentCommandService;
import com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentCommandServiceImpl implements IncidentCommandService {

    private final IncidentRepository incidentRepository;

    public IncidentCommandServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Optional<Incident> handle(CreateIncidentCommand command){
        var incident = new Incident(command);
        incidentRepository.save(incident);
        return Optional.of(incident);
    }

}
