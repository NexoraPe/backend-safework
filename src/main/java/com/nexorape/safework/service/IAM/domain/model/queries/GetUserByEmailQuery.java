package com.nexorape.safework.service.IAM.domain.model.queries;

public record GetUserByEmailQuery(String email) {
    public GetUserByEmailQuery {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is null or empty");
        }
    }
}
