package com.nexorape.safework.service.IAM.interfaces.transform;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.interfaces.resources.CompanyResource;

import com.nexorape.safework.service.IAM.domain.model.aggregates.Company;
import com.nexorape.safework.service.IAM.interfaces.resources.CompanyResource;

public class CompanyResourceFromEntityAssembler {
    public static CompanyResource toResourceFromEntity(Company entity) {
        return new CompanyResource(entity.getId(), entity.getName(), entity.getRegistrationCode().companyCode());
    }
}
