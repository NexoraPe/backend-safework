package com.nexorape.safework.service.IAM.interfaces.rest.transform.user;

import com.nexorape.safework.service.IAM.domain.model.commands.user.SignInCommand;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.user.EmailAddress;
import com.nexorape.safework.service.IAM.interfaces.rest.resources.user.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(new EmailAddress(signInResource.email()), signInResource.password());
    }
}