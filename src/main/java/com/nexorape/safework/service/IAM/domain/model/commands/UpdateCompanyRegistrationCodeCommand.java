package com.nexorape.safework.service.IAM.domain.model.commands;

public record UpdateCompanyRegistrationCodeCommand(String newCompanyRegistrationCode) {
    public UpdateCompanyRegistrationCodeCommand {
        if (newCompanyRegistrationCode == null || newCompanyRegistrationCode.isBlank()) {
            throw new IllegalArgumentException("New Company Registration Code cannot be null or blank");
        }
    }
}
