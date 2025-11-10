package com.nexorape.safework.service.IAM.interfaces.rest.transform;

import com.nexorape.safework.service.IAM.domain.model.commands.company.CreateCompanyCommand;
import com.nexorape.safework.service.IAM.interfaces.rest.resources.company.CreateCompanyResource;

public class CreateCompanyCommandFromResourceAssembler {
    public static CreateCompanyCommand toCommandFromResource(CreateCompanyResource resource){
        return new CreateCompanyCommand(
                resource.name());
    }
}
