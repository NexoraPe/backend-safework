package com.nexorape.safework.service.IAM.application.internal.commandservices;


import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.commands.company.CreateCompanyCommand;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.company.RegistrationCode;
import com.nexorape.safework.service.IAM.domain.services.company.CompanyCommandService;
import com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {

    private final CompanyRepository companyRepository;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> handle(CreateCompanyCommand command){

        var company = new Company(command);
        companyRepository.save(company);
        return Optional.of(company);
    }
}
