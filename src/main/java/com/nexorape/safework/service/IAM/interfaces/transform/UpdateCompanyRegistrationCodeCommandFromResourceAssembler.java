package com.nexorape.safework.service.IAM.interfaces.transform;

import com.nexorape.safework.service.IAM.domain.model.commands.UpdateCompanyRegistrationCodeCommand;
import com.nexorape.safework.service.IAM.interfaces.resources.UpdateCompanyRegistrationCodeResource;

public class UpdateCompanyRegistrationCodeCommandFromResourceAssembler {
    public static UpdateCompanyRegistrationCodeCommand toCommandFromResource(UpdateCompanyRegistrationCodeResource resource) {
        return new UpdateCompanyRegistrationCodeCommand(resource.companyId(), resource.newCompanyRegistrationCode());
    }
}
