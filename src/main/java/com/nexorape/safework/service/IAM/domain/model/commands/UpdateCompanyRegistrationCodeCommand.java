package com.nexorape.safework.service.IAM.domain.model.commands;

public record UpdateCompanyRegistrationCodeCommand(Long companyId, String newCompanyRegistrationCode) {
    public UpdateCompanyRegistrationCodeCommand {

        if(companyId == null || companyId <= 0) {
            throw new IllegalArgumentException("companyId is null or empty");
        }

        if (newCompanyRegistrationCode == null || newCompanyRegistrationCode.isBlank()) {
            throw new IllegalArgumentException("New Company Registration Code cannot be null or blank");
        }
    }
}
