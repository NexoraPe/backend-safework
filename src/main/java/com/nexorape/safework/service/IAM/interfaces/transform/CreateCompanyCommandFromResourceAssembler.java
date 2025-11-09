package com.nexorape.safework.service.IAM.interfaces.transform;

import com.nexorape.safework.service.IAM.domain.model.commands.CreateCompanyCommand;
import com.nexorape.safework.service.IAM.interfaces.resources.CreateCompanyResource;

public class CreateCompanyCommandFromResourceAssembler {
    public static CreateCompanyCommand toCommandFromResource(CreateCompanyResource resource){
        return new CreateCompanyCommand(resource.name(), resource.registrationCode());
    }
}
