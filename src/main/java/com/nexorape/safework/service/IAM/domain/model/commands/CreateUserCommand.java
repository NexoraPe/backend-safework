package com.nexorape.safework.service.IAM.domain.model.commands;

public record CreateUserCommand(Long companyId, String fullName, String emailAddress, String passwordHash, String role) {
    public CreateUserCommand {
        if (companyId == null || companyId <= 0) {
            throw new IllegalArgumentException("companyId cannot be null or empty");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("fullName cannot be null or empty");
        }
        if (emailAddress == null || emailAddress.isBlank()) {
            throw new IllegalArgumentException("emailAddress cannot be null or empty");
        }
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("passwordHash cannot be null or empty");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("role cannot be null or empty");
        }
    }
}
