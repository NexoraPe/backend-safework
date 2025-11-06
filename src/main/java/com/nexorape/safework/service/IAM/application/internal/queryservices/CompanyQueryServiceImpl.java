package com.nexorape.safework.service.IAM.application.internal.queryservices;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.domain.model.queries.ExistsByRegistrationCodeQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetAllCompaniesQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetCompanyByIdQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetCompanyByRegistrationCodeQuery;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.RegistrationCode;
import com.nexorape.safework.service.IAM.domain.services.CompanyQueryService;
import com.nexorape.safework.service.IAM.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryServiceImpl implements CompanyQueryService {
    private final CompanyRepository companyRepository;

    public CompanyQueryServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> handle(GetCompanyByRegistrationCodeQuery query){
        // 1. Traduce el String del query al Value Object
        var registrationCode = new RegistrationCode(query.registrationCode());

        // 2. Llama al método del repositorio
        return companyRepository.findByRegistrationCode(registrationCode.companyCode());
    }

    @Override
    public List<Company> handle(GetAllCompaniesQuery query){
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> handle(GetCompanyByIdQuery query){
        return companyRepository.findById(query.companyId());
    }

    @Override
    public boolean handle(ExistsByRegistrationCodeQuery query){
// 1. Traduce el String al Value Object
        var registrationCode = new RegistrationCode(query.registrationCode());

        // 2. Llama al método del repositorio
        return companyRepository.existsByRegistrationCode(registrationCode.companyCode());    }
}
