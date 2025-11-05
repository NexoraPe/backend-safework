package com.nexorape.safework.service.IAM.domain.model.queries;

public record GetCompanyByRegistrationCodeQuery(String registrationCode) {

    public GetCompanyByRegistrationCodeQuery {
        if (registrationCode == null || registrationCode.isBlank()) {
            throw new IllegalArgumentException("Registration code must not be null or blank.");
        }
    }
}
