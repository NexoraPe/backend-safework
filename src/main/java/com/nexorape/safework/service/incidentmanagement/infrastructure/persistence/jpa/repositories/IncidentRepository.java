package com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories;

import java.util.List;

import com.nexorape.safework.service.iam.domain.model.aggregates.Company;
import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findAllByCompany(Company company);
}
