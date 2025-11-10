package com.nexorape.safework.service.IAM.domain.model.queries.user;

import com.nexorape.safework.service.IAM.domain.model.valueobjects.user.EmailAddress;

public record GetUserByEmailQuery(EmailAddress email) {
}
