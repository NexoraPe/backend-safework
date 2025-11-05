package com.nexorape.safework.service.IAM.domain.model.queries;

import com.nexorape.safework.service.shared.domain.model.valueobjects.UserId;

// User id is referencing to the UUID
public record GetUserByUUIDQuery(UserId userId) {
    public GetUserByUUIDQuery {
        if (userId == null || userId.userId() == null ||  userId.userId().isEmpty()) {
            throw new IllegalArgumentException("User id cannot be null or blank");
        }
    }
}
