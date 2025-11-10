package com.nexorape.safework.service.IAM.domain.model.commands.user;

import com.nexorape.safework.service.IAM.domain.model.valueobjects.user.EmailAddress;

public record SignInCommand(EmailAddress emailAddress, String passwordHash) {
}
