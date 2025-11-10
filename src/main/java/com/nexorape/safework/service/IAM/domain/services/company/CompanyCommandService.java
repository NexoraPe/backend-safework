package com.nexorape.safework.service.IAM.domain.services.company;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.commands.company.CreateCompanyCommand;
import com.nexorape.safework.service.IAM.domain.model.commands.company.SeedCompaniesCommand;

import java.util.Optional;

/**
 * Company Command Service
 */
public interface CompanyCommandService {

    /**
     * Handle Create Company Command
     *
     * @param command The {@link CreateCompanyCommand} Command
     * @return A {@link Company} instance if the command is valid, otherwise empty
     */
    Optional<Company> handle(CreateCompanyCommand command);

    void handle(SeedCompaniesCommand command);
}
