package com.nexorape.safework.service.IAM.interfaces.rest.transform.user;

import com.nexorape.safework.service.IAM.domain.model.aggregates.User;
import com.nexorape.safework.service.IAM.domain.model.entities.Role;
import com.nexorape.safework.service.IAM.interfaces.rest.resources.user.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getCompanyId(), user.getFullName(), user.getEmail(), roles);
    }
}