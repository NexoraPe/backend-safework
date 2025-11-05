package com.nexorape.safework.service.IAM.domain.model.commands;

public record UpdateCompanyNameCommand(String newName) {
    public UpdateCompanyNameCommand{
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("New name cannot be null or blank");
        }
    }
}
