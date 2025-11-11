package com.nexorape.safework.service.iam.interfaces.rest.transform.user;

import com.nexorape.safework.service.iam.domain.model.commands.user.SignUpCommand;
import com.nexorape.safework.service.iam.domain.model.entities.Role;
import com.nexorape.safework.service.iam.domain.model.valueobjects.user.EmailAddress;
import com.nexorape.safework.service.iam.interfaces.rest.resources.user.SignUpResource;
import com.nexorape.safework.service.shared.domain.model.valueobjects.CompanyId;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(
                new CompanyId(resource.companyId()),
                resource.fullName(),
                new EmailAddress(resource.emailAddress()),
                resource.password(),
                roles
        );
    }
}