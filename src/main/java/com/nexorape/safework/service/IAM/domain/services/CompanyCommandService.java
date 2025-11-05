package com.nexorape.safework.service.IAM.domain.services;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.commands.CreateCompanyCommand;
import com.nexorape.safework.service.IAM.domain.model.commands.UpdateCompanyNameCommand;
import com.nexorape.safework.service.IAM.domain.model.commands.UpdateCompanyRegistrationCodeCommand;

import java.util.Optional;

public interface CompanyCommandService {

    Optional<Company> handle(CreateCompanyCommand command);

    void handle(UpdateCompanyNameCommand command);

    void handle(UpdateCompanyRegistrationCodeCommand command);
}
