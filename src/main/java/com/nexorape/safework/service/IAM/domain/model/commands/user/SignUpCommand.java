package com.nexorape.safework.service.IAM.domain.model.commands.user;

import com.nexorape.safework.service.IAM.domain.model.entities.Role;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.user.EmailAddress;
import com.nexorape.safework.service.shared.domain.model.valueobjects.CompanyId;

import java.util.List;

public record SignUpCommand(
        CompanyId companyId,
        String fullName,
        EmailAddress emailAddress,
        String passwordHash,
        List<Role> roles) {
}
