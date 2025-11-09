package com.nexorape.safework.service.IAM.interfaces.transform;

import com.nexorape.safework.service.IAM.domain.model.commands.UpdateCompanyNameCommand;
import com.nexorape.safework.service.IAM.interfaces.resources.UpdateCompanyNameResource;

public class UpdateCompanyNameCommandFromResourceAssembler {
    public static UpdateCompanyNameCommand toCommandFromResource(UpdateCompanyNameResource resource) {
        return new UpdateCompanyNameCommand(resource.companyId(), resource.newName());
    }
}
