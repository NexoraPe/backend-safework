package com.nexorape.safework.service.incidentmanagement.application.internal.commandservices;

import com.nexorape.safework.service.iam.infrastructure.persistence.jpa.repositories.CompanyRepository;
import com.nexorape.safework.service.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.CreateIncidentCommand;
import com.nexorape.safework.service.incidentmanagement.domain.services.IncidentCommandService;
import com.nexorape.safework.service.incidentmanagement.infrastructure.persistence.jpa.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentCommandServiceImpl implements IncidentCommandService {

    private final IncidentRepository incidentRepository;

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public IncidentCommandServiceImpl(IncidentRepository incidentRepository,  UserRepository userRepository, CompanyRepository companyRepository) {
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Incident> handle(CreateIncidentCommand command){

        // SOLUCION RAPIDA, MAS ADELANTE YA SEGUIMOS DDD AL 100% XD

        var user = userRepository.findById(command.userId()).orElseThrow(() -> new RuntimeException("User id not found"));;
        var company = companyRepository.findById(command.companyId()).orElseThrow(() -> new RuntimeException("Company id not found"));;

        var incident = new Incident(user, company, command);
        incidentRepository.save(incident);
        return Optional.of(incident);
    }

}
