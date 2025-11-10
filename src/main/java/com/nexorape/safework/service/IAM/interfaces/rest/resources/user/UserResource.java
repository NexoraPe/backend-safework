package com.nexorape.safework.service.IAM.interfaces.rest.resources.user;

import java.util.List;

public record UserResource(Long id, Long companyId, String fullName, String email, List<String> roles) {
}