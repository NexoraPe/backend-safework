package com.nexorape.safework.service.IAM.interfaces.resources;

public record UpdateCompanyNameResource(Long companyId, String newName) {
    public UpdateCompanyNameResource{

        if(companyId == null || companyId <= 0){
            throw new IllegalArgumentException("companyId is null or empty");
        }

        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("New name cannot be null or blank");
        }
    }
}
