package com.nexorape.safework.service.iam.interfaces.rest.resources.user;

public record AuthenticatedUserResource(Long id, String username, String token) {
}