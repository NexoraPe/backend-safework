package com.nexorape.safework.service.IAM.domain.model.commands;

public record UpdateUserPersonalInfoCommand(String newFullName, String newPhoneNumber) {
    public UpdateUserPersonalInfoCommand {
        if (newFullName == null || newFullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }
    }
}
