package com.nexorape.safework.service.incidentmanagement.interfaces.rest.transform.assignment;

import com.nexorape.safework.service.incidentmanagement.domain.model.entities.Assignment;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.assignment.AssignmentResource;

public class AssignmentResourceFromEntityAssembler {
    public  static AssignmentResource toResourceFromEntity(Assignment entity){
        return new AssignmentResource(
                entity.getIncidentId(),
                entity.getUserId(),
                entity.getPriority(),
                entity.getCompletionDate()
        );
    }
}
