package com.nexorape.safework.service.incidentmanagement.domain.services;

import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.GetAllIncidentsQuery;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.GetIncidentByIdQuery;

import java.util.List;
import java.util.Optional;


public interface IncidentQueryService {
    Optional<Incident> handle(GetIncidentByIdQuery query);
    List<Incident> handle(GetAllIncidentsQuery query);
}
