package com.nexorape.safework.service.IAM.domain.model.commands;

public record UpdateUserProfilePictureCommand(String newProfilePictureUrl) {
    public UpdateUserProfilePictureCommand {
        if(newProfilePictureUrl == null || newProfilePictureUrl.isBlank()) {
            throw new IllegalArgumentException("New profile picture url cannot be null or blank");
        }
    }
}
