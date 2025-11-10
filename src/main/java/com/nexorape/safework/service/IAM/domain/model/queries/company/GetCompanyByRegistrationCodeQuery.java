package com.nexorape.safework.service.IAM.domain.model.queries.company;

import com.nexorape.safework.service.IAM.domain.model.valueobjects.company.RegistrationCode;

public record GetCompanyByRegistrationCodeQuery(RegistrationCode registrationCode) {
}
