package com.nexorape.safework.service.iam.application.internal.commandservices;


import com.nexorape.safework.service.iam.domain.model.aggregates.Company;
import com.nexorape.safework.service.iam.domain.model.commands.company.CreateCompanyCommand;
import com.nexorape.safework.service.iam.domain.model.commands.company.SeedCompaniesCommand;
import com.nexorape.safework.service.iam.domain.services.company.CompanyCommandService;
import com.nexorape.safework.service.iam.infrastructure.persistence.jpa.repositories.CompanyRepository;
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

    @Override
    public void handle(SeedCompaniesCommand command) {

        companyRepository.save(new Company("Tralaleritos"));
        companyRepository.save(new Company("Cocainomanos"));

    }
}
