package com.nexorape.safework.service.iam.application.internal.commandservices;

import com.nexorape.safework.service.iam.domain.model.aggregates.Company;
import com.nexorape.safework.service.iam.domain.model.commands.company.CreateCompanyCommand;
import com.nexorape.safework.service.iam.domain.model.commands.company.SeedCompaniesCommand;
import com.nexorape.safework.service.iam.domain.services.company.CompanyCommandService;
import com.nexorape.safework.service.iam.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Application service implementation that handles company-related commands in the IAM context.
 * - Creates companies via CreateCompanyCommand.
 * - Seeds default companies via SeedCompaniesCommand.
 * Delegates persistence to CompanyRepository.
 */

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {

    private final CompanyRepository companyRepository;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> handle(CreateCompanyCommand command) {

        var checkCompany = companyRepository.findByName(command.name());
        if (checkCompany.isPresent())
            throw new RuntimeException("Company already exists");

        var company = new Company(command);
        companyRepository.save(company);
        return Optional.of(company);
    }

    @Override
    public void handle(SeedCompaniesCommand command) {
        // Definimos los nombres que queremos sembrar en una lista
        List<String> companyNamesToSeed = Arrays.asList("Tralaleritos", "Cocainomanos");

        // Usamos streams para iterar de forma concisa
        companyNamesToSeed.stream().forEach(companyName -> {
            // 1. Usamos el método optimizado existsByName()
            if (!companyRepository.existsByName(companyName)) {

                // 2. Si no existe, creamos y guardamos la nueva entidad
                Company newCompany = new Company(companyName);
                companyRepository.save(newCompany);

                System.out.println("Seeded company: " + companyName);
            } else {
                System.out.println("Company already exists, skipping seed: " + companyName);
            }
        });
    }
}
