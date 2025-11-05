package com.nexorape.safework.service.IAM.domain.services;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.queries.GetAllCompaniesQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetCompanyByIdQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetCompanyByRegistrationCodeQuery;

import java.util.List;
import java.util.Optional;

public interface CompanyQueryService {
    Optional<Company> handle(GetCompanyByRegistrationCodeQuery query);

    List<Company> handle(GetAllCompaniesQuery query);

    Optional<Company> handle(GetCompanyByIdQuery query);
}
