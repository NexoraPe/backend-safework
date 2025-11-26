package com.nexorape.safework.service.incidentmanagement.application.internal.queryservices;

import com.nexorape.safework.service.iam.infrastructure.persistence.jpa.repositories.CompanyRepository;
import com.nexorape.safework.service.incidentmanagement.domain.model.aggregates.Incident;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.incident.GetAllIncidentsByCompanyQuery;
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
    private final CompanyRepository companyRepository;

    public IncidentQueryServiceImpl(IncidentRepository incidentRepository, CompanyRepository companyRepository) {
        this.incidentRepository = incidentRepository;
        this.companyRepository = companyRepository;
    }

    // QUERIES

    @Override
    public Optional<Incident> handle(GetIncidentByIdQuery query) {
        return incidentRepository.findById(query.incidentId());
    }

    @Override
    public List<Incident> handle(GetAllIncidentsQuery query) {
        var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication();
        var userDetails = (com.nexorape.safework.service.iam.infrastructure.authorization.sfs.model.UserDetailsImpl) authentication
                .getPrincipal();
        var role = userDetails.getAuthorities().stream().findFirst().map(Object::toString).orElse("");

        if (role.equals("ADMIN")) {
            return incidentRepository.findAll();
        } else {

            var company = companyRepository.findById(userDetails.getCompanyId());

            return incidentRepository.findAllByCompany(company.get());

        }
    }

    @Override
    public List<Incident> handle(GetAllIncidentsByCompanyQuery query) {

        return incidentRepository.findAllByCompany(query.company());
    }

}
