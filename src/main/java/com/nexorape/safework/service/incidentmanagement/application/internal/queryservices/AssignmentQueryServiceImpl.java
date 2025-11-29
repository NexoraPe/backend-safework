package com.nexorape.safework.service.incidentmanagement.application.internal.queryservices;

import com.nexorape.safework.service.incidentmanagement.domain.model.entities.Assignment;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.assignment.GetAllAssignmentsQuery;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.assignment.GetAssignmentByIdQuery;
import com.nexorape.safework.service.incidentmanagement.domain.services.AssignmentQueryService;
import com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentQueryServiceImpl implements AssignmentQueryService {
    private final AssignmentRepository assignmentRepository;

    public AssignmentQueryServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    // QUERIES
    @Override
    public Optional<Assignment> handle(GetAssignmentByIdQuery query) {
        return assignmentRepository.findById(query.assignmentId());
    }

    @Override
    public List<Assignment> handle(GetAllAssignmentsQuery query) {
        return assignmentRepository.findAll();
    }

    @Override
    public List<Assignment> handle(
            com.nexorape.safework.service.incidentmanagement.domain.model.queries.assignment.GetAssignmentsByUserIdQuery query) {
        return assignmentRepository.findByUserId(query.userId());
    }

}
