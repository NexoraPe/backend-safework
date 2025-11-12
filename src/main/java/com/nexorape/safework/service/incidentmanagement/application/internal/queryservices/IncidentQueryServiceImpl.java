package com.nexorape.safework.service.incidentmanagement.application.internal.queryservices;


import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.incident.GetAllIncidentsQuery;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.incident.GetIncidentByIdQuery;
import com.nexorape.safework.service.incidentmanagement.domain.services.IncidentQueryService;
import com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IncidentQueryServiceImpl implements IncidentQueryService {

    private final IncidentRepository incidentRepository;

    public IncidentQueryServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    //QUERIES

    @Override
    public Optional<Incident> handle(GetIncidentByIdQuery query){
        return incidentRepository.findById(query.incidentId());
    }

    @Override
    public List<Incident> handle(GetAllIncidentsQuery query){
        return incidentRepository.findAll();
    }


}
