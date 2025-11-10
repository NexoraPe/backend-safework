package com.nexorape.safework.service.IAM.interfaces.rest.resources.user;

public record AuthenticatedUserResource(Long id, String username, String token) {
}