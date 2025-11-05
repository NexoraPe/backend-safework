package com.nexorape.safework.service.IAM.domain.model.commands;

public record CreateCompanyCommand(String name, String registrationCode) {
    public CreateCompanyCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (registrationCode == null || registrationCode.isBlank()) {
            throw new IllegalArgumentException("registrationCode cannot be null or empty");
        }
    }
}
