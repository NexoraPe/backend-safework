package com.nexorape.safework.service.IAM.interfaces.rest.transform.role;

import com.nexorape.safework.service.IAM.domain.model.entities.Role;
import com.nexorape.safework.service.IAM.interfaces.rest.resources.role.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}