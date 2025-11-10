package com.nexorape.safework.service.IAM.application.internal.queryservices;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.queries.company.GetAllCompaniesQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.company.GetCompanyByIdQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.company.GetCompanyByRegistrationCodeQuery;
import com.nexorape.safework.service.IAM.domain.services.company.CompanyQueryService;
import com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryServiceImpl implements CompanyQueryService {

    // Repositorio
    private final CompanyRepository companyRepository;

    //Constructor
    public CompanyQueryServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Others
    @Override
    public Optional<Company> handle(GetCompanyByIdQuery query){
        return companyRepository.findById(query.companyId());
    }

    @Override
    public List<Company> handle(GetAllCompaniesQuery query){
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> handle(GetCompanyByRegistrationCodeQuery query){
        return companyRepository.findByRegistrationCode(query.registrationCode());
    }
}
