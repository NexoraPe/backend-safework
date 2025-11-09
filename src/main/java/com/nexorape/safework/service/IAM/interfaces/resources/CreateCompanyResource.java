package com.nexorape.safework.service.IAM.interfaces.resources;

public record CreateCompanyResource(String name, String registrationCode) {
    public CreateCompanyResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (registrationCode == null || registrationCode.isBlank()) {
            throw new IllegalArgumentException("registrationCode cannot be blank");
        }
    }
}
