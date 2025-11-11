package com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories;

import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
