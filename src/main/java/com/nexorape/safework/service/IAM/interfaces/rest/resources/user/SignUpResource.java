package com.nexorape.safework.service.IAM.interfaces.rest.resources.user;

import java.util.List;

public record SignUpResource(Long companyId, String fullName, String emailAddress, String password, List<String> roles) {
}