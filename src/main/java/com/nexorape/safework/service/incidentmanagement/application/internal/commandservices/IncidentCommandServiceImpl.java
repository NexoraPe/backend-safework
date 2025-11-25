package com.nexorape.safework.service.incidentmanagement.application.internal.commandservices;

import com.nexorape.safework.service.iam.domain.model.entities.Role;
import com.nexorape.safework.service.iam.domain.model.valueobjects.Roles;
import com.nexorape.safework.service.iam.infrastructure.persistence.jpa.repositories.CompanyRepository;
import com.nexorape.safework.service.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.assignment.CreateAssignmentCommand;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.incident.CloseIncidentCommand;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.incident.CreateIncidentCommand;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.incident.StartIncidentProgressCommand;
import com.nexorape.safework.service.incidentmanagement.domain.model.entities.Assignment;
import com.nexorape.safework.service.incidentmanagement.domain.model.events.IncidentStatusChangedEvent;
import com.nexorape.safework.service.incidentmanagement.domain.services.IncidentCommandService;
import com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentCommandServiceImpl implements IncidentCommandService {

        private final IncidentRepository incidentRepository;
        private final UserRepository userRepository;
        private final CompanyRepository companyRepository;
        private final org.springframework.context.ApplicationEventPublisher eventPublisher;

        public IncidentCommandServiceImpl(IncidentRepository incidentRepository, UserRepository userRepository,
                        CompanyRepository companyRepository,
                        org.springframework.context.ApplicationEventPublisher eventPublisher) {
                this.incidentRepository = incidentRepository;
                this.userRepository = userRepository;
                this.companyRepository = companyRepository;
                this.eventPublisher = eventPublisher;
        }

        @Override
        public Optional<Incident> handle(CreateIncidentCommand command) {

                // SOLUCION RAPIDA, MAS ADELANTE YA SEGUIMOS DDD AL 100% XD

                var user = userRepository.findById(command.userId())
                                .orElseThrow(() -> new RuntimeException("User not found"));
                var company = companyRepository.findById(command.companyId())
                                .orElseThrow(() -> new RuntimeException("Company not found"));

                var incident = new Incident(user, company, command);
                incidentRepository.save(incident);

                eventPublisher.publishEvent(
                                new com.nexorape.safework.service.incidentmanagement.domain.model.events.IncidentCreatedEvent(
                                                incident.getId(),
                                                incident.getTitle(),
                                                incident.getDescription(),
                                                incident.getCompanyId(),
                                                incident.getUserId()));

                return Optional.of(incident);
        }

        // ASSIGMENTS
        @Override
        public Optional<Assignment> handle(CreateAssignmentCommand command) {
                var incident = incidentRepository.findById(command.incidentId())
                                .orElseThrow(() -> new RuntimeException("Incident not found"));
                var user = userRepository.findById(command.userId())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // VALIDACIÓN DE ROL
                // List<String>
                var roles = user.getRoles().stream().map(Role::getStringName).toList();

                if (!roles.contains(Roles.EMPLOYER.name())) {
                        throw new IllegalArgumentException(
                                        "Error: Solo se pueden asignar incidentes a usuarios con rol EMPLOYEER.");
                }

                incident.assignTo(user);

                // NO LLAMAR AL REPOSITORIO DE ASSIGMENT, EL INCIDENT YA SE ENCARGA DE LA
                // CREACION EN LA BASE DE DATOS
                // SOLO ESTA PARA LECTURA EN EL QUERYSERVICES

                incidentRepository.save(incident);

                eventPublisher.publishEvent(
                                new com.nexorape.safework.service.incidentmanagement.domain.model.events.IncidentAssignedEvent(
                                                incident.getId(),
                                                user.getId()));

                return Optional.of(incident.getAssignment());
        }

        @Override
        public Optional<Incident> handle(StartIncidentProgressCommand command) {
                var incident = incidentRepository.findById(command.incidentId())
                                .orElseThrow(() -> new RuntimeException("Incident not found"));
                incident.startProgress();
                incidentRepository.save(incident);
                eventPublisher.publishEvent(new IncidentStatusChangedEvent(
                                incident.getId(),
                                incident.getStatus(),
                                command.userId()));
                return Optional.of(incident);
        }

        @Override
        public Optional<Incident> handle(CloseIncidentCommand command) {
                var incident = incidentRepository.findById(command.incidentId())
                                .orElseThrow(() -> new RuntimeException("Incident not found"));
                incident.close();
                incidentRepository.save(incident);
                eventPublisher.publishEvent(new IncidentStatusChangedEvent(
                                incident.getId(),
                                incident.getStatus(),
                                command.userId()));
                return Optional.of(incident);
        }
}
