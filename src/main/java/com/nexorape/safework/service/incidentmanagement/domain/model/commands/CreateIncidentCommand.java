package com.nexorape.safework.service.incidentmanagement.domain.model.commands;

import com.nexorape.safework.service.iam.domain.model.aggregates.Company;
import com.nexorape.safework.service.iam.domain.model.aggregates.User;

public record CreateIncidentCommand(
        Long userId,
        Long companyId,
        String title,
        String description,
        String location) {
}
