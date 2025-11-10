package com.nexorape.safework.service.IAM.interfaces.rest.transform.company;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.interfaces.rest.resources.company.CompanyResource;

public class CompanyResourceFromEntityAssembler {
    public static CompanyResource toResourceFromEntity(Company entity) {
        return new CompanyResource(
                entity.getId(),
                entity.getName(),
                entity.getRegistrationCode());
    }
}
