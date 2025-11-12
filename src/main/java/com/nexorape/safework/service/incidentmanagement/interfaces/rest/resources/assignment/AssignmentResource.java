package com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.assignment;

import java.util.Date;

public record AssignmentResource(
        Long incidentId,
        Long userId,
        String priority,
        Date completionDate
) {
}
