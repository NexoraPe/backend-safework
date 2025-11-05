package com.nexorape.safework.service.IAM.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record RegistrationCode(String companyCode) {

    public RegistrationCode {
        if (companyCode == null || companyCode.isEmpty()) {
            throw new IllegalArgumentException("CompanyCode cannot be null or empty");
        }
    }
}
