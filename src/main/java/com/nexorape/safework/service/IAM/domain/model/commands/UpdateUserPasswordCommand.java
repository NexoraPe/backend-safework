package com.nexorape.safework.service.IAM.domain.model.commands;

import java.util.Objects;

public record UpdateUserPasswordCommand(String oldPassword, String newPassword) {
    public UpdateUserPasswordCommand {
        if (!Objects.equals(oldPassword, newPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (newPassword == null ||  newPassword.isBlank()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }
    }
}
