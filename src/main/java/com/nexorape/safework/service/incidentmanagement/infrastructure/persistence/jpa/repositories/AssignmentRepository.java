package com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories;

import com.nexorape.safework.service.incidentmanagement.domain.model.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
}
