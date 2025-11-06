package com.nexorape.safework.service.IAM.domain.model.commands;

public record UpdateCompanyNameCommand(Long companyId, String newName) {
    public UpdateCompanyNameCommand{

        if(companyId == null || companyId <= 0){
            throw new IllegalArgumentException("companyId is null or empty");
        }

        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("New name cannot be null or blank");
        }
    }
}
