package com.nexorape.safework.service.IAM.interfaces;

import com.nexorape.safework.service.IAM.domain.model.queries.GetAllCompaniesQuery;
import com.nexorape.safework.service.IAM.domain.model.queries.GetCompanyByIdQuery;
import com.nexorape.safework.service.IAM.domain.services.CompanyCommandService;
import com.nexorape.safework.service.IAM.domain.services.CompanyQueryService;
import com.nexorape.safework.service.IAM.interfaces.resources.CompanyResource;
import com.nexorape.safework.service.IAM.interfaces.resources.CreateCompanyResource;
import com.nexorape.safework.service.IAM.interfaces.resources.UpdateCompanyNameResource;
import com.nexorape.safework.service.IAM.interfaces.resources.UpdateCompanyRegistrationCodeResource;
import com.nexorape.safework.service.IAM.interfaces.transform.CompanyResourceFromEntityAssembler;
import com.nexorape.safework.service.IAM.interfaces.transform.CreateCompanyCommandFromResourceAssembler;
import com.nexorape.safework.service.IAM.interfaces.transform.UpdateCompanyNameCommandFromResourceAssembler;
import com.nexorape.safework.service.IAM.interfaces.transform.UpdateCompanyRegistrationCodeCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/companies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Companies", description = "Company Management Endpoints")
public class CompaniesController {
    private final CompanyCommandService companyCommandService;
    private final CompanyQueryService companyQueryService;

    public CompaniesController(CompanyCommandService companyCommandService, CompanyQueryService companyQueryService) {
        this.companyCommandService = companyCommandService;
        this.companyQueryService = companyQueryService;
    }

    @PostMapping
    public ResponseEntity<CompanyResource> createCompany(@RequestBody CreateCompanyResource resource){
        var createCompanyCommand = CreateCompanyCommandFromResourceAssembler.toCommandFromResource(resource);
        var company = companyCommandService.handle(createCompanyCommand);
        if(company.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return new ResponseEntity<>(companyResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllCompanies(){
        var getAllCompaniesQuery = new GetAllCompaniesQuery();
        var companies = companyQueryService.handle(getAllCompaniesQuery);
        var companiesResources = companies.stream().map(CompanyResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(companiesResources);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResource> getCompanyById(@PathVariable Long companyId){
        var getCompanyByIdQuery = new GetCompanyByIdQuery(companyId);
        var company = companyQueryService.handle(getCompanyByIdQuery);
        if(company.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return ResponseEntity.ok(companyResource);
    }

    @PutMapping("/{companyId}/name")
    public ResponseEntity<CompanyResource> updateCompanyName(@PathVariable Long companyId, @RequestBody UpdateCompanyNameResource resource){
        if(!companyId.equals(resource.companyId())){
            return ResponseEntity.badRequest().build();
        }
        var updateCompanyNameCommand = UpdateCompanyNameCommandFromResourceAssembler.toCommandFromResource(resource);
        companyCommandService.handle(updateCompanyNameCommand);
        var getCompanyByIdQuery = new GetCompanyByIdQuery(companyId);
        var updatedCompany = companyQueryService.handle(getCompanyByIdQuery);

        if(updatedCompany.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(updatedCompany.get());
        return ResponseEntity.ok(companyResource);
    }

    @PutMapping("/{companyId}/registration-code")
    public ResponseEntity<CompanyResource> updateCompanyRegistrationCode(@PathVariable Long companyId, @RequestBody UpdateCompanyRegistrationCodeResource resource){
        if(!companyId.equals(resource.companyId())){
            return ResponseEntity.badRequest().build();
        }
        var updateCompanyRegistrationCodeCommand = UpdateCompanyRegistrationCodeCommandFromResourceAssembler.toCommandFromResource(resource);
        companyCommandService.handle(updateCompanyRegistrationCodeCommand);
        var getCompanyByIdQuery = new GetCompanyByIdQuery(companyId);
        var updatedCompany = companyQueryService.handle(getCompanyByIdQuery);

        if(updatedCompany.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(updatedCompany.get());
        return ResponseEntity.ok(companyResource);
    }
}


