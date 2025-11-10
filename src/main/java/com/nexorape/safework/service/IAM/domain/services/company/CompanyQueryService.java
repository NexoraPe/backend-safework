package com.nexorape.safework.service.IAM.domain.services.company;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.queries.company.GetAllCompaniesQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.company.GetCompanyByIdQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.company.GetCompanyByRegistrationCodeQuery;

import java.util.List;
import java.util.Optional;

public interface CompanyQueryService {
    Optional<Company> handle(GetCompanyByIdQuery query);

    List<Company> handle(GetAllCompaniesQuery query);

    Optional<Company> handle(GetCompanyByRegistrationCodeQuery query);
}
