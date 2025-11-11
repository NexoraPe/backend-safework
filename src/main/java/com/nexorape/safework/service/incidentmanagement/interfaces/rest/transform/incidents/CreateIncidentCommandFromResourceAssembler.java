package com.nexorape.safework.service.incidentmanagement.interfaces.rest.transform.incidents;

import com.nexorape.safework.service.incidentmanagement.domain.model.commands.CreateIncidentCommand;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.incident.CreateIncidentResource;

public class CreateIncidentCommandFromResourceAssembler {
    public static CreateIncidentCommand toCommandFromResource(CreateIncidentResource resource) {
        return new CreateIncidentCommand(
                resource.title(),
                resource.description(),
                resource.location()
        );
    }
}
